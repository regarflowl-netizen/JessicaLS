<div align="center">

# JessicaLS Framework

**Fork & rebranding terkendali berbasis [JingMatrix/Vector](https://github.com/JingMatrix/Vector) — ART hooking framework modern untuk Android**

</div>

## 1. Tentang JessicaLS

**JessicaLS** (JessicaLS Framework & JessicaLS Manager) adalah fork berbasis [JingMatrix/Vector](https://github.com/JingMatrix/Vector) (commit baseline `6f6b976e`). JessicaLS mempertahankan seluruh mesin, arsitektur, mekanisme hooking, dan kompatibilitas API Xposed dari Vector upstream, sambil menyinkronkan identitas produk, package (`org.jessicals` / `org.jessicals.manager`), module ID (`zygisk_jessicals`), proses daemon (`jessicalsd`), Binder rendezvous (`serial_jessicals`), serta aset branding di seluruh lapisan proyek.

## 2. Arsitektur & Teknologi Utama

- **Integrasi Zygisk:** Modul native `libzygisk.so` (`:zygisk`) menangani spesialisasi proses aplikasi dan `system_server`, injeksi DEX in-memory melalui `InMemoryDexClassLoader`, serta isolasi mount namespace (`unshare -m` + `mount -o rslave`).
- **LSPlant ART Hooking:** Menggunakan submodule upstream `external/lsplant` untuk hooking ART dengan prefix kelas hasil generate `JessicaLS_`, dipadukan dengan `external/dobby` (inline hooking) dan `external/lsplt` (PLT hooking untuk wrapper `dex2oat`).
- **Modern libxposed API:** Menyediakan dukungan API modern `io.github.libxposed.api.*` dan `io.github.libxposed.service.*` melalui submodule upstream `xposed/libxposed` dan `services/libxposed` tanpa perubahan kontrak API.
- **Legacy Xposed API:** Mempertahankan penuh API `de.robv.android.xposed.*` dan `XResources` pada modul `:legacy` agar modul Xposed legacy tetap kompatibel.
- **Manager, Daemon & IPC:**
  - Daemon root berjalan sebagai `jessicalsd` (`org.jessicals.daemon.JessicaLSDaemon`) dengan CLI `JessicaLSCli` (`org.jessicals.daemon.Cli`).
  - Komunikasi IPC menggunakan AIDL di bawah namespace `org.jessicals.ipc.*` (`IJessicaLSDaemon`, `IManagerService`, `IFrameworkService`, dll.).
  - Parasitic Manager berjalan di dalam proses host sistem `com.android.shell` (UID `2000`) melalui `BugreportWarningActivity` + kategori `org.jessicals.manager.LAUNCH_MANAGER`, dan menerima Binder melalui handshake `org.jessicals.manager.Constants.setBinder(IBinder)`.
  - Direktori data `/data/adb/lspd` dipertahankan pada versi awal untuk menjaga kompatibilitas konfigurasi dan mencegah kehilangan data pengguna.

## 3. Persyaratan Instalasi & Build

### Persyaratan Perangkat (Runtime)
- Perangkat Android dengan akses root (Magisk, KernelSU, atau APatch) dan implementasi Zygisk aktif (misalnya NeoZygisk / Zygisk bawaan).
- Konfigurasi SDK upstream menargetkan Android 8.1+ (`minSdk 27`, `compileSdk 37`, 16 KB page alignment `-Wl,-z,max-page-size=16384`).

### Persyaratan Toolchain Build
- JDK 21 (`JavaVersion.VERSION_21`)
- Android SDK 37 & Build Tools `37.0.0`
- Android NDK `30.0.16248370`
- CMake `3.29.8+` (C++23)
- Perintah build utama:
  ```bash
  ./gradlew :manager:assembleDebug
  ./gradlew :zygisk:zipDebug
  ./gradlew :zygisk:zipAll
  ```

## 4. Instalasi & Uninstall

1. Bangun atau unduh paket ZIP rilis `JessicaLS-v<version>-<code>-<variant>.zip`.
2. Pasang melalui root manager yang mendukung Zygisk (Magisk / KernelSU / APatch).
3. Installer (`customize.sh`) akan memverifikasi SHA-256 setiap berkas, mendeteksi modul konflik (`zygisk_lsposed`, `zygisk_vector`) dan menonaktifkannya secara aman tanpa menghapus data di `/data/adb/lspd`.
4. Reboot perangkat, lalu buka **JessicaLS Manager** melalui notifikasi sistem atau shortcut launcher.
5. Untuk uninstall, hapus modul `zygisk_jessicals` melalui root manager dan reboot. Skrip `uninstall.sh` hanya membersihkan file runtime sementara (`.cli_sock`, `lock`) dan **mempertahankan** `/data/adb/lspd` agar konfigurasi modul, database, dan log pengguna tidak hilang.

## 5. Troubleshooting & Pengumpulan Log

Gunakan `logcat` dengan tag internal JessicaLS untuk menelusuri masalah daemon, Manager, atau pemuatan modul:

```bash
adb logcat -s JessicaLS JessicaLSManager JessicaLSLogcat JessicaLSContext JessicaLSLegacyBridge JessicaLSModuleManager
```

Log juga tersedia di tab **Logs** pada JessicaLS Manager (stream `modules` dan `verbose`).

## 6. Kompatibilitas & Keterbatasan Saat Ini

- **Pengujian Perangkat:** Belum dilakukan pengujian runtime langsung pada perangkat fisik ber-root dalam lingkungan audit ini (status pengujian device: `NOT TESTED` — lihat `JESSICALS_FINAL_AUDIT.md`).
- **Build Lokal Sandbox:** Kompilasi Gradle/NDK memerlukan host atau CI runner dengan JDK 21, Android SDK 37, NDK `30.0.16248370`, dan CMake `3.29.8+`.

## 7. Atribusi & Lisensi

JessicaLS merupakan proyek turunan (fork) dari **[Vector](https://github.com/JingMatrix/Vector)** karya **JingMatrix** beserta seluruh proyek hulu dan dependensi open-source berikut:

- [Vector (JingMatrix/Vector)](https://github.com/JingMatrix/Vector) — basis upstream utama
- [LSPlant](https://github.com/JingMatrix/LSPlant) — mesin ART hooking inti
- [Dobby](https://github.com/JingMatrix/Dobby) — inline hooking
- [LSPlt](https://github.com/JingMatrix/LSPlt) — PLT hooking
- [libxposed-api](https://github.com/JingMatrix/libxposed-api) & [libxposed-service](https://github.com/JingMatrix/libxposed-service)
- [LSPosed](https://github.com/LSPosed/LSPosed), [EdXposed](https://github.com/ElderDrivers/EdXposed), [XposedBridge](https://github.com/rovo89/XposedBridge), [Magisk](https://github.com/topjohnwu/Magisk/), [xz-embedded](https://github.com/tukaani-project/xz-embedded)

Proyek ini didistribusikan di bawah lisensi **[GNU General Public License v3.0 (GPL-3.0)](LICENSE)** sesuai lisensi upstream Vector. Seluruh pemberitahuan hak cipta dan lisensi pihak ketiga tetap dipertahankan.
