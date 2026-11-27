# JessicaLS — Performance Benchmark Plan & Measurement Log (Phase 6)

> **Catatan Kejujuran Pengukuran:** Lingkungan sandbox ini tidak memiliki perangkat Android fisik ber-root yang terhubung melalui ADB, sehingga angka runtime sebelum/sesudah berstatus **`PENDING DEVICE MEASUREMENT`**. Tabel di bawah menyediakan baseline teoritis/kode yang terverifikasi serta perintah exact untuk mengambil angka aktual pada perangkat Anda setelah build GitHub Actions selesai.

---

## 1. Perbandingan Baseline vs Target Terukur

| Skenario | Mekanisme Sebelum Optimasi | Mekanisme Sesudah Optimasi | Status Pengukuran Perangkat |
|---|---|---|---|
| **A. Cold/Warm Startup Splash** | Serial: `binderHandshake` + `800ms` delay + `320ms` crossfade (minimal **1.120 ms** dead wait) | Parallel overlap: `max(handshake, 260ms)` + `180ms` crossfade (**~440 ms** total saat binder siap) | `PENDING DEVICE MEASUREMENT` |
| **B. Startup Binder Contention** | `prefetch()` memanggil `getInstalledApps(filterNoProcess=true)` (~350–500 IPC `getPackageInfoWithComponents`) | `prefetch()` memanggil `moduleScanPackages(false)` (1 enumerasi paket tanpa scan komponen) | `PENDING DEVICE MEASUREMENT` |
| **C. Cold APK Module Scan** | Sekuensial single-thread atas ~350–500 APK (`mapNotNull`) | Paralel terbatas (`Dispatchers.IO.limitedParallelism(4)`) + cache persisten | `PENDING DEVICE MEASUREMENT` |
| **D. Install 1 Modul Baru (Event-to-UI)** | 3–5 broadcast memicu 3–5x invalidasi cache & race `loadModules()` + `loadFacts()` memicu `getInstalledApps(filterNoProcess=true)` | `debounce(250)` + `invalidatePackage(pkg)` + `loadJob.cancel()` + `loadFacts()` memakai ulang `moduleScanPackages(false)` | `PENDING DEVICE MEASUREMENT` |
| **E. Background / Covered GPU Usage** | `AmbientSurface` terus menjalankan `withFrameNanos` saat tidak `RESUMED` | Dipause otomatis melalui `repeatOnLifecycle(Lifecycle.State.RESUMED)` | `PENDING DEVICE MEASUREMENT` |

---

## 2. Perintah Pengambilan Benchmark Aktual di Perangkat (ADB)

Setelah memasang build ZIP hasil GitHub Actions terbaru pada perangkat ber-root:

```bash
# 1. Bersihkan buffer logcat dan pantau metrik diagnostik monotonik JessicaLSManager
adb logcat -c
adb logcat -s JessicaLSManager JessicaLS JessicaLSModuleManager

# 2. Ukur cold startup time Manager (standalone atau via action.sh)
adb shell am force-stop com.android.shell
adb shell su -c "sh /data/adb/modules/zygisk_jessicals/action.sh"

# 3. Uji deteksi modul baru (catat waktu selesai install vs baris log 'modules: scanned ...')
adb install path/to/test-xposed-module.apk
```

Cari baris log berikut di output `logcat`:
`modules: scanned <N> pkgs (<M> modules, opened <K>) in <total>ms [users=<U>ms enum=<E>ms inspect=<I>ms]`
