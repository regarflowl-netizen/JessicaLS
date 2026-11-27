# JessicaLS — Performance Optimization Summary (Phases 2–5)

Seluruh perubahan bersifat minimal, terisolasi pada 6 file, dan terhubung langsung ke bottleneck yang diverifikasi pada `JESSICALS_PERFORMANCE_AUDIT.md`.

---

## Daftar File yang Dimodifikasi & Detail Implementasi

| # | File | Masalah Diselesaikan | Perubahan Teknis |
|---|---|---|---|
| 1 | `manager/src/main/kotlin/org/jessicals/manager/ui/screens/splash/SplashScreen.kt` | **UI-1** (1.120 ms artificial startup stall) | `ANIMATION_MS` diturunkan dari `800L` ke `260L`, tumpang-tindih dengan handshake Binder menggunakan `SystemClock.elapsedRealtime()` (hanya menunda sisa waktu bila handshake <260 ms), dan durasi `Crossfade` dipersingkat dari `320ms` ke `180ms`. |
| 2 | `manager/src/main/kotlin/org/jessicals/manager/di/ServiceLocator.kt` | **UI-2** (startup IPC storm) & **MOD-2** (broadcast burst) | `prefetch()` kini menghangatkan `apps.moduleScanPackages(false)` (tanpa `filterNoProcess = true`) alih-alih `getInstalledApps()`. `observePackageChanges()` menambahkan `moduleDetection.invalidatePackage(pkg)` per-paket + `.debounce(250)` untuk menggabungkan badai broadcast instalasi/update menjadi 1 invalidasi & refresh bersih. |
| 3 | `manager/src/main/kotlin/org/jessicals/manager/data/model/ModuleDetectionCache.kt` | **MOD-1** (thread safety paralel) & **MOD-2** (invalidasi inkremental) | Mengubah `inspectedThisRun` menjadi `AtomicInteger` (aman untuk pemindaian paralel), menambahkan `resetRunCounter()` per siklus scan, dan `invalidatePackage(packageName)` untuk invalidasi metadata 1 paket terdampak tanpa menghapus cache paket lain. |
| 4 | `manager/src/main/kotlin/org/jessicals/manager/ui/screens/modules/ModulesViewModel.kt` | **UI-2**, **MOD-1**, **MOD-2**, & **Phase 5 Diagnostics** | 1) Menambahkan `loadJob` & `factsJob` untuk membatalkan scan lama yang tersusul. 2) Memparalelkan `detection.inspect(...)` dengan `Dispatchers.IO.limitedParallelism(4)` + `coroutineScope { ... awaitAll() }`. 3) Mengganti `getInstalledApps()` di `loadFacts()` dengan `moduleScanPackages(false)` yang sudah hangat di cache (menghilangkan 350–500 IPC `getPackageInfoWithComponents`). 4) Menambahkan log diagnostik waktu monotonik (`usersMs`, `enumMs`, `inspectMs`, `opened`, `totalMs`) pada tag `JessicaLSManager`. |
| 5 | `manager-ui/src/main/kotlin/org/jessicals/ui/ambience/AmbientSurface.kt` | **UI-3** (continuous background Canvas loop & draw-phase state write) | Membungkus loop `withFrameNanos` dengan `lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED)` dan mengganti `mutableStateOf(Size.Zero)` dengan plain holder object agar draw phase tidak memicu invalidasi state. |
| 6 | `daemon/src/main/kotlin/org/jessicals/daemon/data/Database.kt` | **MOD-3** (full table scan pada `scope`) | Menambahkan `CREATE INDEX IF NOT EXISTS scope_app_pkg_idx ON scope (app_pkg_name);` di `onOpen()` (idempoten, tanpa mengubah `DB_VERSION = 5` atau memigrasi ulang tabel). |
