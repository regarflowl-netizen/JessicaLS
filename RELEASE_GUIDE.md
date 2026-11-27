# Panduan Rilis JessicaLS (Siap Eksekusi ke GitHub Actions & Uji Perangkat)

Repositori fork **JessicaLS** sudah lolos seluruh pemeriksaan statis (termasuk perbaikan P0-1, P0-2, P1, dan P2 tanpa mengubah engine LSPlant, Zygisk, atau mekanisme hooking). Karena GitHub Actions pada `.github/workflows/core.yml` sudah dikonfigurasi penuh dengan JDK 21, Android SDK 37, NDK `30.0.16248370`, CMake, dan `./gradlew zipAll`, jalur tercepat dan paling bersih untuk menghasilkan APK & ZIP rilis adalah mendorong **Git bundle** ini ke GitHub.

---

## Langkah 1 — Buat Repositori Publik di GitHub

1. Buka [GitHub — New Repository](https://github.com/new) menggunakan akun **`regarflowl-netizen`**.
2. Isi **Repository name:** `JessicaLS`.
3. Pilih **Public** (wajib agar endpoint GitHub Releases dapat dibaca oleh JessicaLS Manager tanpa token di dalam APK).
4. **Jangan** centang *Add a README*, *.gitignore*, atau *license* (biarkan kosong).
5. Klik **Create repository**.

---

## Langkah 2 — Ekstrak Git Bundle & Push ke GitHub

Jalankan perintah berikut di terminal komputer lokal Anda (tempat Anda menyimpan `JessicaLS-Release-Ready.bundle`):

```bash
git clone JessicaLS-Release-Ready.bundle JessicaLS
cd JessicaLS

# Pastikan seluruh 9 submodule upstream ter-checkout pada commit pinned aslinya
git submodule update --init --recursive

# Verifikasi integritas commit & submodule
git rev-parse HEAD
git submodule status --recursive

# Arahkan remote ke repositori publik Anda dan push branch master beserta tag
git remote set-url origin https://github.com/regarflowl-netizen/JessicaLS.git
git push -u origin master
git push origin --tags
```

---

## Langkah 3 — Jalankan Build Penuh via GitHub Actions

1. Setelah `git push`, buka tab **Actions** pada `https://github.com/regarflowl-netizen/JessicaLS/actions`.
2. Workflow **`core.yml`** akan otomatis berjalan (memasang JDK 21, Android SDK 37, NDK `30.0.16248370`, menjalankan `./gradlew zipAll`, dan mengunggah `JessicaLS-v*-Release.zip` serta `JessicaLS-v*-Debug.zip`).
3. Untuk menerbitkan rilis resmi pertama (sehingga Manager dapat menguji fitur in-app updater):
   ```bash
   git tag v2.2.0-jessicals.1
   git push origin v2.2.0-jessicals.1
   ```
   Workflow akan otomatis membuat GitHub Release **`JessicaLS v2.2.0-jessicals.1`** beserta lampiran `JessicaLS-v*-Release.zip` dan `JessicaLS-v*-Debug.zip`.

> **Opsional (Signing Key Rilis):** Jika ingin APK ditandatangani dengan keystore rilis sendiri (bukan debug key bawaan), tambahkan secret CI sesuai konfigurasi `.github/workflows/core.yml` sebelum melakukan push tag.

---

## Langkah 4 — Verifikasi Isi ZIP Rilis

Setelah mengunduh `JessicaLS-v*-Release.zip` dari GitHub Releases, verifikasi strukturnya:

```bash
unzip -l JessicaLS-v*-Release.zip
```

Pastikan berisi:
- `module.prop` (`id=zygisk_jessicals`, `name=JessicaLS`)
- `manager.apk` dan `daemon.apk`
- `framework/jessicals.dex`
- `lib/*/libzygisk.so` dan `bin/*/{dex2oat,liboat_hook.so}`
- File checksum `.sha256` untuk setiap berkas

---

## Langkah 5 — Uji pada Perangkat Android Ber-Root (Zygisk)

1. Pasang `JessicaLS-v*-Debug.zip` terlebih dahulu melalui Magisk / KernelSU / APatch (dengan Zygisk aktif), lalu **reboot**.
2. Pantau log boot dan daemon:
   ```bash
   adb logcat -s JessicaLS JessicaLSManager JessicaLSLogcat JessicaLSContext JessicaLSLegacyBridge JessicaLSModuleManager
   ```
3. Verifikasi checklist runtime:
   - [ ] Daemon `jessicalsd` berjalan dan `serial_jessicals` (late-inject) / `serial` (boot) terhubung.
   - [ ] Parasitic Manager terbuka lewat notifikasi / `action.sh` dan handshake `Constants.setBinder(IBinder)` sukses.
   - [ ] 1 modul Xposed legacy (`de.robv.android.xposed.*`) dan 1 modul libxposed API 101 aktif dan berhasil hook.
   - [ ] Tab pembaruan di Manager membaca rilis dari `regarflowl-netizen/JessicaLS` dan hanya menampilkan aset `JessicaLS-*.zip`.
   - [ ] Uninstall modul lewat root manager + reboot tidak menghapus data di `/data/adb/lspd`.
