# JessicaLS Repository Audit (Phase 1)

## 1. Informasi Repositori Upstream

- **Upstream URL:** `https://github.com/JingMatrix/Vector`
- **Branch:** `master`
- **Base Commit:** `6f6b976e` (`Build the Store's catalogue from two hosts, in manager-ui (#1022)`)
- **Working Tree Awal:** Clean (fresh recursive clone)
- **Lisensi Upstream:** GNU General Public License v3.0 (`LICENSE`, 35,149 bytes)

## 2. Daftar Modul Gradle Aktual (`settings.gradle.kts`)

Seluruh 13 modul yang didefinisikan pada `settings.gradle.kts` terverifikasi ada:

| Modul | Peran Utama | Namespace Upstream |
|---|---|---|
| `:daemon` | Daemon proses root (`app_process`), manajemen modul, IPC server, CLI, logcat & DEX obfuscation JNI | `org.matrix.vector.daemon` |
| `:dex2oat` | Wrapper `dex2oat` dan PLT hook (`liboat_hook.so`) via LSPlt | `org.matrix.vector.dex2oat` |
| `:external:axml` | ManifestEditor upstream (vendored) | Submodule upstream |
| `:external:apache` | Apache Commons Lang upstream (vendored) | Submodule upstream |
| `:hiddenapi:stubs` | Stubs hidden API Android (`android.os.*`, `android.content.*`, dll.) | Internal stubs |
| `:hiddenapi:bridge` | Bridge akses hidden API | Internal bridge |
| `:legacy` | Implementasi API Xposed legacy (`de.robv.android.xposed.*`, `XResources`) | `org.matrix.vector.legacy` |
| `:manager` | Aplikasi pengelola Jetpack Compose (standalone & Parasitic Manager) | `org.matrix.vector.manager` |
| `:manager-ui` | Lapisan UI Compose bersama (theme seed, ambience, network/DoH, panel bar) | `org.matrix.vector.ui` |
| `:services:daemon-service` | Kontrak AIDL internal daemon + submodule `libxposed-service` | `org.matrix.vector.daemonservice` |
| `:services:manager-service` | Kontrak AIDL antara Manager dan Daemon | `org.matrix.vector.managerservice` |
| `:xposed` | Implementasi modern libxposed API 101 + submodule `libxposed-api` | `org.matrix.vector.impl` |
| `:zygisk` | Modul Zygisk native (`libzygisk.so`), DEX entry point, packaging ZIP release | `org.matrix.vector` |

## 3. Sembilan Git Submodule Upstream (Dipertahankan Tanpa Perubahan)

Seluruh commit submodule di-pin sesuai upstream:

| Submodule | Pinned Commit |
|---|---|
| `external/apache/commons-lang` | `7b6048b7a9d1339a12d353cb85b2222df7bf5668` |
| `external/axml/manifest-editor` | `c238e28f3a1a0275c38945aa4f646b065d3f5889` |
| `external/dobby` | `05a09ac6807a6bb1726350e40ea4b127c1c79809` |
| `external/fmt` | `5da4e9a3fb15626ba92969199061e0169ebc8a00` |
| `external/lsplant` | `7a768e31ffb2852797631b5140b54f9702a78743` |
| `external/lsplt` | `a89012ef2d1dbc62b6fc205cb941f00a9c9a3d18` |
| `external/xz-embedded` | `ae63ae3a36ed01724674e8f3d750dc47bf125410` |
| `services/libxposed` | `3318940876192e29cf6ab07637e899e22a87ebf0` |
| `xposed/libxposed` | `39cac0845771547c9c67a3e3ce255af110a54a0e` |

Nested submodules di bawah `external/lsplant` (`docs/doxygen-awesome-css`, `dex_builder`, `parallel_hashmap`) juga dipertahankan sesuai commit upstream.

## 4. Toolchain Aktual Upstream vs Lingkungan Eksekusi

| Parameter | Konfigurasi Upstream (`build.gradle.kts` / `libs.versions.toml`) | Lingkungan Sandbox VM |
|---|---|---|
| Gradle Wrapper | `9.8.2` | Tersedia (`./gradlew`) |
| AGP | `9.5.0` | Membutuhkan JDK 21 + Android SDK 37 |
| Kotlin | `2.4.21` | Bundled via Gradle |
| Java Compatibility | `VERSION_21` | OpenJDK `17.0.21` (JDK 21 tidak terpasang) |
| `compileSdk` / `targetSdk` | `37` | Android SDK 37 tidak terpasang |
| `buildToolsVersion` | `37.0.0` | Tidak terpasang |
| `minSdk` | `27` | - |
| `ndkVersion` | `30.0.16248370` | NDK tidak terpasang |
| CMake | `3.29.8+` (C++23) | Tidak terpasang |
| ABI Filters | `arm64-v8a`, `armeabi-v7a`, `x86`, `x86_64` | - |
| Linker Alignment | `-Wl,-z,max-page-size=16384` (16 KB page size) | Dipertahankan pada `build.gradle.kts` |
| C++ Macros | `-DPHMAP_HAVE_SSE2=0`, `-DPHMAP_HAVE_SSSE3=0` | Dipertahankan pada `build.gradle.kts` |

**Status Build Baseline:** **BLOCKED (Toolchain Tidak Tersedia di VM)**. Lingkungan sandbox hanya memiliki OpenJDK 17 dengan ruang disk tersisa 5.7 GB, tanpa Android SDK 37, Build Tools 37.0.0, NDK `30.0.16248370`, atau CMake `3.29.8+`. Konfigurasi toolchain upstream tidak diubah dan siap dikompilasi pada mesin host atau GitHub Actions CI yang memiliki toolchain tersebut.

## 5. Peta Arsitektur & Analisis Graphify (AST)

Ekstraksi AST Graphify pada seluruh kode proyek (mengecualikan `external/`, `services/libxposed`, `xposed/libxposed`) menghasilkan:
- **Total File Kode Dipindai:** 386 file
- **Graf Pengetahuan:** 5,342 node, 13,500 edge, 261 komunitas (`graphify-out/graph.json`, `graphify-out/GRAPH_REPORT.md`)
- **Top God Nodes (pusat ketergantungan lintas lapisan):**
  1. `IBinder` (degree 109) — inti IPC Binder lintas Zygisk, Daemon, dan Manager
  2. `XposedHelpers` (degree 108) — jembatan reflection legacy Xposed (`de.robv.android.xposed`, wajib dipertahankan)
  3. `XResources` (degree 92) — resource hooking legacy (wajib dipertahankan & terisolasi oleh `checkXResourcesIsolation`)
  4. `Context` (degree 73) & `Intent` (degree 72) — integrasi sistem Android & Parasitic Manager
  5. `DaemonClient` (degree 64) — klien IPC utama pada `:manager` menuju `IManagerService`

## 6. Entry Points & Alur Komunikasi Manager–Daemon–Zygisk

1. **Boot & Isolasi Mount Namespace:** `zygisk/module/service.sh` menjalankan `unshare -m` + `mount -o rslave none /` lalu memanggil `zygisk/module/daemon`.
2. **Daemon Process:** `zygisk/module/daemon` melakukan bind-mount kosong ke `/data/resource-cache` dan meluncurkan `app_process --nice-name=vectord org.matrix.vector.daemon.VectorDaemon`.
3. **Rendezvous Binder:** Daemon mendaftarkan proxy Binder pada `serial` (saat boot normal) atau `serial_vector` (saat late-inject setelah `system_server` aktif).
4. **Zygisk Injection:** `zygisk/src/main/cpp/module.cpp` (`VectorModule`) meminta Binder via `IPCBridge`, mengambil `framework/vector.dex` (terobfuskasi melalui `obfuscation.cpp`) + `obfuscation_map`, memuat DEX in-memory via `InMemoryDexClassLoader`, menginisialisasi LSPlant (`generated_class_name = "Vector_"`), dan memanggil `org.matrix.vector.core.Main.forkCommon(...)`.
5. **Parasitic Manager Handshake:** `action.sh` / shortcut meluncurkan `com.android.shell/.BugreportWarningActivity` dengan kategori `<managerPackage>.LAUNCH_MANAGER`. `ParasiticManagerSystemHooker` mengalihkan resolusi aktivitas dan `ParasiticManagerHooker.sendBinderToManager` memanggil `<managerPackage>.Constants.setBinder(IBinder)` melalui reflection.
6. **Direktori Data:** `FileSystem.kt` dan `uninstall.sh` menggunakan `/data/adb/lspd`. Sesuai spesifikasi Bagian 3.2, path ini **dipertahankan** untuk kompatibilitas dan keamanan data pengguna.

## 7. Risiko Utama Package / JNI / AIDL / IPC

- **JNI Symbol Mangling:** `dex2oat.cpp` (3 simbol), `logcat.cpp` (1 simbol), `obfuscation.cpp` (2 simbol), dan `native/include/jni/jni_bridge.h` (`Java_org_matrix_vector_nativebridge_*`) harus 100% sinkron dengan package Kotlin/Java.
- **DEX Obfuscation Map Keys:** `obfuscation.cpp`, `module.cpp`, `ipc_bridge.cpp`, dan `jni_bridge.h` menggunakan key prefix `org.matrix.vector.core.`, `org.matrix.vector.nativebridge.`, dan `org.matrix.vector.service.` — kegagalan sinkronisasi di salah satu sisi akan menyebabkan `std::out_of_range` / `ClassNotFoundException` saat runtime.
- **Logcat Binary Search:** `kModuleTags` di `logcat.cpp` dicari dengan `std::binary_search`; urutan alfabetis array WAJIB tetap terjaga setelah rename ke `JessicaLS*`.
- **ProGuard/R8 & `checkXResourcesIsolation`:** Keep rules di `:daemon`, `:manager`, `:zygisk`, `:legacy`, `:xposed`, serta `resourceOwners` di `zygisk/build.gradle.kts` wajib diperbarui agar R8 tidak menghapus `Constants.setBinder`, `Main.forkCommon`, `BridgeService`, atau `JessicaLSNativeHooker`.
