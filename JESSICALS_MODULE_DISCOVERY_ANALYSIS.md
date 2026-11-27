# JessicaLS — Xposed Module Discovery Pipeline Analysis (Phase 3)

## 1. Pemetaan 10 Tahap Pipeline Deteksi Modul

| Tahap | Komponen | Mekanisme Aktual | Bottleneck Sebelumnya | Perbaikan |
|---|---|---|---|---|
| 1. Instalasi paket Android | Android PackageInstaller | Memasang APK & menerbitkan broadcast `PACKAGE_ADDED` / `REPLACED` | Normal | Dipertahankan |
| 2. Visibilitas PackageManager | `JessicaLSService.kt` (daemon `USER_ALL`) & `PackageBroadcasts.kt` (manager) | Dual-source broadcast (`packageEventsFlow` + `daemonPackageEventsFlow`) | 1 instalasi menghasilkan 3–5 event beruntun (<100 ms) tanpa debounce | `.debounce(250)` menggabungkan burst menjadi 1 siklus refresh bersih |
| 3. Deteksi kelayakan modul | `ModuleDetection.inspect()` | Memeriksa `META-INF/xposed/java_init.list` (modern) & meta-data `xposedminversion` (legacy) | Dieksekusi sekuensial di 1 thread untuk ~350–500 APK | Paralelisasi terbatas (`Dispatchers.IO.limitedParallelism(4)`) |
| 4. Ekstraksi metadata Xposed | `ModuleDetectionCache` (`module-detection.tsv`) | Cache persisten berbasis `(packageName, versionCode, lastUpdateTime)` | Counter non-atomik; tidak ada invalidasi per-paket eksplisit | `AtomicInteger` + `invalidatePackage(pkg)` per event |
| 5. Sinkronisasi DB modul | `ModuleDatabase` + `Database.kt` | WAL mode + `PRAGMA synchronous=NORMAL` di `/data/adb/lspd/config/modules_config.db` | Query `app_pkg_name` pada `scope` melakukan full table scan | Indeks `scope_app_pkg_idx ON scope (app_pkg_name)` di `onOpen()` |
| 6. Pembaruan state Daemon | `ConfigCache.requestCacheUpdate()` | Rebuild eventual-consistent di background thread terpisah dari Binder | Normal (dipertahankan) | Dipertahankan |
| 7. Notifikasi Binder / Broadcast | `JessicaLSService` → `ACTION_DAEMON_NOTIFICATION` | Re-broadcast lintas user ke Manager | Memicu `apps.invalidate()` ganda yang membatalkan cache in-flight | Digabungkan oleh `debounce(250)` |
| 8. Refresh Repository Manager | `AppRepository.moduleScanPackages()` + `ModuleRepository.refresh()` | Single-flight `Deferred` berbagi hasil enumerasi | `loadFacts()` memicu enumerasi kedua `getInstalledApps(filterNoProcess=true)` | `loadFacts()` memakai ulang `moduleScanPackages(false)` |
| 9. Pembaruan State ViewModel | `ModulesViewModel` | `_discovered` → `userModulesTabs` (`.flowOn(Dispatchers.Default)`) → `loadFacts` | `loadModules()` tidak membatalkan job sebelumnya saat burst | `loadJob?.cancel()` & `factsJob?.cancel()` |
| 10. Rendering Compose UI | `ModulesScreen` | `LazyColumn` dengan stable keys & `AppIconCache` | `AmbientSurface` & `SplashScreen` bersaing saat startup | Gating lifecycle `RESUMED` & pengurangan stall splash |

---

## 2. Pemisahan Tegas: Deteksi vs Pemuatan (Loading)

- **Detected / Installed:** Modul ditemukan oleh `ModuleDetection.inspect()` dan muncul di daftar `ModulesScreen`.
- **Enabled / Scope Configured:** Diatur di `ModuleDatabase` (`modules.enabled = 1` dan tabel `scope`).
- **Loaded / Hook Initialized:** Hanya terjadi saat proses target (atau `system_server`) dispesialisasi oleh Zygisk, memuat `jessicals.dex` + modul APK via `JessicaLSModuleClassLoader`, dan mengeksekusi hook melalui LSPlant. Kegagalan pemuatan aktual dilaporkan oleh daemon via `IManagerService.getModuleLoadFailures()` ke `ModuleFacts.loadFailure`.
- Optimasi ini **tidak** mengubah status menjadi "Loaded" hanya karena modul terdeteksi di Manager.
