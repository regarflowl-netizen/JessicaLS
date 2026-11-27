# JessicaLS — Performance Audit (Phase 1)

**Base:** JessicaLS fork (`org.jessicals` / `org.jessicals.manager`, synced with `Core #15` green build)  
**Scope:** `manager/`, `manager-ui/`, `daemon/`, `services/manager-service/`, `services/daemon-service/`, `xposed/`, `legacy/`, `native/`, `zygisk/`  
**Method:** Static architectural & hot-path inspection (Jetpack Compose, Kotlin Coroutines, SQLite, Binder IPC, and Module Discovery pipeline).

---

## 1. Bottleneck Teridentifikasi — Problem A (Manager UI/UX Performance)

| ID | Lokasi | Akar Masalah Terverifikasi | Dampak Nyata |
|---|---|---|---|
| **UI-1** | `manager/.../ui/screens/splash/SplashScreen.kt` | `SplashGate` menunggu daemon binder terlebih dahulu, **lalu secara serial** menjalankan `delay(800L)` + `Crossfade(tween(320))` = **1.120 ms** penundaan buatan bahkan ketika Binder sudah terhubung instan (0 ms). | Startup terasa lambat (>1,1 detik dead wait) pada setiap peluncuran cold/warm. |
| **UI-2** | `manager/.../di/ServiceLocator.kt` (`prefetch()`) & `ModulesViewModel.kt` (`loadFacts()`) | `prefetch()` dan `loadFacts()` memanggil `apps.getInstalledApps()`, yang memanggil `daemonClient.getInstalledPackagesFromAllUsers(flags, filterNoProcess = true)`. Di `daemon/.../SystemExtensions.kt:256`, `filterNoProcess = true` memicu `getPackageInfoWithComponents()` via Binder untuk **setiap aplikasi terpasang (350–500 panggilan IPC)** ke `system_server`. | Saturasi Binder thread pool & IPC heap tepat saat startup dan saat membuka tab Modules, membuat interaksi awal tersendat. |
| **UI-3** | `manager-ui/.../ui/ambience/AmbientSurface.kt` | Loop `withFrameNanos` berjalan kontinu tanpa gating lifecycle (`RESUMED`), dan menulis `var canvasSize by remember { mutableStateOf(...) }` di dalam blok draw `Canvas`. | Pemborosan siklus GPU/CPU saat layar di background/tertutup detail screen dan penjadwalan state write saat draw phase. |

### Area UI yang Sudah Baik (Tidak Diubah Tanpa Bukti)
- `AppIconCache` (`manager-ui/.../AppIcon.kt`) sudah menggunakan `LruCache` 12 MB dan rasterisasi di `Dispatchers.IO`.
- `ModulesViewModel.userModulesTabs` sudah memakai `.flowOn(Dispatchers.Default)` untuk filtering/sorting search agar tidak memblokir main thread.
- `HomeScreen` dan `ModulesScreen` sudah memakai `LazyColumn` dengan `key` stabil dan `collectAsStateWithLifecycle()`.

---

## 2. Bottleneck Teridentifikasi — Problem B (Xposed Module Detection Delay)

| ID | Lokasi | Akar Masalah Terverifikasi | Dampak Nyata |
|---|---|---|---|
| **MOD-1** | `manager/.../ui/screens/modules/ModulesViewModel.kt` (`discover()`) | Pemindaian `packages.mapNotNull { detection.inspect(...) }` berjalan **sekuensial di satu thread** atas seluruh 350–500+ paket (hingga ~550 pembukaan ZIP APK + split APK saat cache dingin/miss). | Penundaan multi-detik saat pemindaian awal atau saat cache terinvalidasi. |
| **MOD-2** | `manager/.../di/ServiceLocator.kt` (`observePackageChanges()`) & `ModulesViewModel.kt` | Memasang/memperbarui 1 modul memicu **3–5 broadcast** beruntun dalam <100 ms (`PACKAGE_REMOVED`, `PACKAGE_ADDED`, `PACKAGE_REPLACED`, ditambah `ACTION_DAEMON_NOTIFICATION` dari daemon). Tanpa debounce, `apps.invalidate()` dipanggil berulang kali sehingga `generation` tidak cocok, hasil enumerasi in-flight **dibuang (tidak di-cache)**, dan `loadModules()` diluncurkan berkali-kali secara bersamaan tanpa membatalkan job lama. | Badai enumerasi penuh ke daemon dan race condition pada pembaruan daftar modul. |
| **MOD-3** | `daemon/.../data/Database.kt` | Tabel `scope` hanya memiliki primary key `(mid, app_pkg_name, user_id)` (kolom depan `mid`). Query `isEnabledScopeTarget(appPackage)` dan `systemServerModuleRows()` mencari berdasarkan `app_pkg_name`, sehingga memicu full table scan pada tabel `scope`. | Overhead query SQLite meningkat seiring bertambahnya modul dan jumlah target scope. |

---

## 3. Invarian Arsitektur yang Dipertahankan

Seluruh komponen berikut diaudit dan **tidak diubah**:
- Zygisk native module (`zygisk/src/main/cpp/`), LSPlant, Dobby, LSPlt, `dex2oat`.
- Kontrak AIDL (`org.jessicals.ipc.*`), `Constants.setBinder(IBinder)`, dan host Parasitic Manager (`com.android.shell`).
- API publik Xposed legacy (`de.robv.android.xposed.*`) dan modern libxposed API 101 (`io.github.libxposed.*`).
- Skema database `/data/adb/lspd/config/modules_config.db` (versi tetap `DB_VERSION = 5`).
