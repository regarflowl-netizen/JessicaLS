# JessicaLS Branding Mapping & Change Plan (Phase 2)

## 1. Klasifikasi Identifier

### A. Branding / User-Facing (Diubah ke JessicaLS)

| Komponen | Nilai Lama | Nilai Baru | Producer / Consumer Utama |
|---|---|---|---|
| Display / Project Name | `Vector` | `JessicaLS` | `settings.gradle.kts`, `README.md`, `module.prop`, `customize.sh`, `strings.xml` (semua locale), `.github/workflows/core.yml` |
| Manager Display Name | `Vector` / `Vector Manager` | `JessicaLS` / `JessicaLS Manager` | `manager/src/main/res/values*/strings.xml`, `manager/src/debug/AndroidManifest.xml` |
| Notification Strings | `Vector status`, `Vector loaded` | `JessicaLS status`, `JessicaLS loaded` | `daemon/src/main/res/values*/strings.xml` |
| Launcher & Brand Icon | `ic_winged_victory` | Giraffe Logo (`logo.png` -> adaptive icon foreground + mipmap) | `manager/src/main/res/mipmap-anydpi-v26/ic_launcher.xml`, `manager/src/main/res/drawable/` |
| Default Theme Seed Color | `0xFF6ABFCF` (Cyan) | `0xFFE8A838` (Warm Amber/Ochre) | `manager-ui/.../SeedScheme.kt`, `manager/.../SettingsRepository.kt` |
| Android Theme Style | `Theme.Vector` | `Theme.JessicaLS` | `manager/src/main/res/values/themes.xml`, `manager/src/main/AndroidManifest.xml` |

### B. Internal Identifier Milik Vector (Wajib Diubah & Disinkronkan Lintas Lapisan)

| Komponen | Nilai Lama | Nilai Baru | Producer & Consumer |
|---|---|---|---|
| Base Package / Namespace | `org.matrix.vector` | `org.jessicals` | Seluruh source `.kt`/`.java`/`.aidl`, `build.gradle.kts` (`daemon`, `dex2oat`, `legacy`, `manager`, `manager-ui`, `services/*`, `xposed`, `zygisk`), `proguard-rules.pro`, `consumer-rules.pro`, `AndroidManifest.xml` |
| Manager Application ID | `org.matrix.vector.manager` | `org.jessicals.manager` | `build.gradle.kts` (`defaultManagerPackageName`), `manager/build.gradle.kts`, `manager/src/main/AndroidManifest.xml`, `manager/proguard-rules.pro`, `zygisk/build.gradle.kts`, `ParasiticManagerHooker.kt`, `ParasiticManagerSystemHooker.kt` |
| Zygisk Module ID | `zygisk_vector` | `zygisk_jessicals` | `zygisk/module/module.prop`, `zygisk/module/cli`, `zygisk/module/customize.sh`, `zygisk/build.gradle.kts` (membaca dinamis dari `module.prop`) |
| Injected Framework DEX | `vector.dex` | `jessicals.dex` | `zygisk/build.gradle.kts`, `zygisk/module/customize.sh`, `daemon/.../FileSystem.kt` |
| Daemon Process Nice-Name | `vectord` | `jessicalsd` | `zygisk/module/daemon` |
| CLI Process Nice-Name | `VectorCli` | `JessicaLSCli` | `zygisk/module/cli` |
| Binder Rendezvous Service | `serial_vector` | `serial_jessicals` | `daemon/.../JessicaLSDaemon.kt`, `zygisk/src/main/cpp/module.cpp`, `zygisk/module/sepolicy.rule`, `zygisk/README.md` |
| LSPlant Generated Class Prefix | `Vector_` | `JessicaLS_` | `zygisk/src/main/cpp/module.cpp` (`InitInfo.generated_class_name`) |
| C++ Namespace | `vector::native` | `jessicals::native` | `native/include/**`, `native/src/**`, `zygisk/src/main/cpp/**` |
| JNI Exported Symbols | `Java_org_matrix_vector_*` | `Java_org_jessicals_*` | `daemon/src/main/jni/{dex2oat,logcat,obfuscation}.cpp`, `native/include/jni/jni_bridge.h` |
| DEX Obfuscation Prefixes | `Lorg/matrix/vector/{core,nativebridge,service}/` | `Lorg/jessicals/{core,nativebridge,service}/` | `daemon/src/main/jni/obfuscation.cpp`, `native/include/jni/jni_bridge.h`, `zygisk/src/main/cpp/{module,ipc_bridge}.cpp` |
| Log Tags | `Vector`, `VectorManager`, `VectorLogcat`, `VectorContext`, `VectorLegacyBridge`, `VectorModuleManager`, `VectorNative`, `VectorNativeDaemon`, `VectorDex2Oat`, `VectorManagerService` | `JessicaLS`, `JessicaLSManager`, `JessicaLSLogcat`, `JessicaLSContext`, `JessicaLSLegacyBridge`, `JessicaLSModuleManager`, `JessicaLSNative`, `JessicaLSNativeDaemon`, `JessicaLSDex2Oat`, `JessicaLSManagerService` | `daemon/src/main/jni/logcat.cpp`, `daemon/src/main/jni/logging.h`, `dex2oat/src/main/cpp/include/logging.h`, `native/include/common/logging.h`, `LogcatMonitor.kt`, `Constants.kt`, `XposedBridge.java`, `JessicaLSContext.kt`, `JessicaLSModuleManager.kt`, `ManagerService.kt`, `zygisk/module/daemon` |
| Release ZIP Artifact | `Vector-v<ver>-<code>-<variant>.zip` | `JessicaLS-v<ver>-<code>-<variant>.zip` | `zygisk/build.gradle.kts`, `.github/workflows/core.yml` |
| CMake Root Variable | `VECTOR_ROOT` | `JESSICALS_ROOT` | `build.gradle.kts`, `native/CMakeLists.txt`, `daemon/src/main/jni/CMakeLists.txt`, `dex2oat/src/main/cpp/CMakeLists.txt`, `zygisk/src/main/cpp/CMakeLists.txt` |
| CI Environment Variables | `VECTOR_BUILD_REPOSITORY`, `VECTOR_BUILD_COMMIT` | `JESSICALS_BUILD_REPOSITORY`, `JESSICALS_BUILD_COMMIT` | `build.gradle.kts`, `.github/workflows/core.yml` |
| Internal Product Classes | `IVectorDaemon`, `VectorDaemon`, `VectorService`, `VectorContext`, `VectorApp`, `VectorModuleManager`, `VectorNativeHooker`, dll. | `IJessicaLSDaemon`, `JessicaLSDaemon`, `JessicaLSService`, `JessicaLSContext`, `JessicaLSApp`, `JessicaLSModuleManager`, `JessicaLSNativeHooker`, dll. | Seluruh modul internal (sinkron antara deklarasi, import, ProGuard/R8, `zygisk/module/daemon`, dan JNI) |

### C. Public API & Dependency Identifiers (WAJIB DIPERTAHANKAN)

- `de.robv.android.xposed.*` (`:legacy`)
- `android.content.res.XResources`, `XModuleResources` (`:legacy`)
- `io.github.libxposed.api.*` (`xposed/libxposed`)
- `io.github.libxposed.service.*` (`services/libxposed`)
- `hidden.*` (`:hiddenapi:stubs`, `:hiddenapi:bridge`)
- Seluruh 9 git submodules (`external/*`, `services/libxposed`, `xposed/libxposed`)
- Logcat compatibility tags: `XSharedPreferences`, `LSPosed`, `dex2oat`, `zygisk`, `LSPlant`, `LSPlt`, `Dobby`, `Magisk`, `KernelSU`, `APatchD`

### D. Android System & Data Compatibility Identifiers (WAJIB DIPERTAHANKAN)

- Host Parasitic Manager: `com.android.shell`, UID `2000`, `.BugreportWarningActivity`
- Handshake method signature: `Constants.setBinder(android.os.IBinder): Boolean`
- Intent category suffix: `.LAUNCH_MANAGER` (menjadi `org.jessicals.manager.LAUNCH_MANAGER`)
- Direktori data daemon: `/data/adb/lspd` (`FileSystem.kt`, `TroubleshootScreen.kt`, `uninstall.sh`) — dipertahankan sesuai Bagian 3.2 untuk kompatibilitas migrasi dan keamanan data pengguna.
- Standard `serial` service name untuk boot-time rendezvous (hanya late-inject diubah dari `serial_vector` ke `serial_jessicals`).

## 2. Tabel Pemetaan Descriptor AIDL

| Old AIDL Descriptor | New AIDL Descriptor | Deklarasi | Pemanggil / Implementasi |
|---|---|---|---|
| `org.matrix.vector.ipc.IVectorDaemon` | `org.jessicals.ipc.IJessicaLSDaemon` | `services/daemon-service/.../IJessicaLSDaemon.aidl` | `:daemon` (`JessicaLSDaemon`, `JessicaLSService`), `:zygisk` |
| `org.matrix.vector.ipc.IFrameworkService` | `org.jessicals.ipc.IFrameworkService` | `services/daemon-service/.../IFrameworkService.aidl` | `:daemon`, `:xposed`, `:zygisk` |
| `org.matrix.vector.ipc.IModuleService` | `org.jessicals.ipc.IModuleService` | `services/daemon-service/.../IModuleService.aidl` | `:daemon`, `:xposed` |
| `org.matrix.vector.ipc.IProcessChannel` | `org.jessicals.ipc.IProcessChannel` | `services/daemon-service/.../IProcessChannel.aidl` | `:daemon`, `:xposed` |
| `org.matrix.vector.ipc.IRemotePreferenceCallback` | `org.jessicals.ipc.IRemotePreferenceCallback` | `services/daemon-service/.../IRemotePreferenceCallback.aidl` | `:daemon`, `:xposed` |
| `org.matrix.vector.ipc.IHotReloadOutcomeReceiver` | `org.jessicals.ipc.IHotReloadOutcomeReceiver` | `services/daemon-service/.../IHotReloadOutcomeReceiver.aidl` | `:daemon`, `:xposed` |
| `org.matrix.vector.ipc.HotReloadOutcome` | `org.jessicals.ipc.HotReloadOutcome` | `services/daemon-service/.../HotReloadOutcome.aidl` | `:daemon`, `:xposed` |
| `org.matrix.vector.ipc.LoadedModule` | `org.jessicals.ipc.LoadedModule` | `services/daemon-service/.../LoadedModule.aidl` | `:daemon`, `:xposed`, `services/daemon-service/build.gradle.kts` (`aidlPackagedList`) |
| `org.matrix.vector.ipc.ModuleCode` | `org.jessicals.ipc.ModuleCode` | `services/daemon-service/.../ModuleCode.aidl` | `:daemon`, `:xposed` |
| `org.matrix.vector.ipc.IManagerService` | `org.jessicals.ipc.IManagerService` | `services/manager-service/.../IManagerService.aidl` | `:daemon` (`ManagerService`), `:manager` (`Constants`, `DaemonClient`), `:zygisk` |
| `org.matrix.vector.ipc.IFrameworkInstallReceiver` | `org.jessicals.ipc.IFrameworkInstallReceiver` | `services/manager-service/.../IFrameworkInstallReceiver.aidl` | `:daemon`, `:manager` |
| `org.matrix.vector.ipc.DeviceUser` | `org.jessicals.ipc.DeviceUser` | `services/manager-service/.../DeviceUser.aidl` | `:daemon`, `:manager` |
| `org.matrix.vector.ipc.ModuleLoadFailure` | `org.jessicals.ipc.ModuleLoadFailure` | `services/manager-service/.../ModuleLoadFailure.aidl` | `:daemon`, `:manager` |
| `org.matrix.vector.ipc.ScopeEntry` | `org.jessicals.ipc.ScopeEntry` | `services/manager-service/.../ScopeEntry.aidl` | `:daemon`, `:manager` |
