# JessicaLS — Regression Test Report (Phase 7)

## 1. Pemeriksaan Statis & Integritas Arsitektur

| Komponen | Diubah? | Status Verifikasi Statis | Keterangan |
|---|---|---|---|
| Zygisk native (`zygisk/src/main/cpp/`) | **Tidak** | **PASS** | Tidak ada file C++/JNI yang disentuh. |
| LSPlant, Dobby, LSPlt, 9 Submodules | **Tidak** | **PASS** | Seluruh 9 submodule tetap bersih pada commit pinned upstream. |
| AIDL & Binder IPC (`services/*`) | **Tidak** | **PASS** | Kontrak `IManagerService`, `IJessicaLSDaemon`, dan `Constants.setBinder` utuh. |
| Legacy Xposed API (`de.robv.android.xposed.*`) | **Tidak** | **PASS** | Tidak ada perubahan signature atau perilaku. |
| Modern libxposed API 101 (`io.github.libxposed.*`) | **Tidak** | **PASS** | Tidak ada perubahan signature atau perilaku. |
| SQLite Schema Compatibility (`DB_VERSION = 5`) | **Indeks saja** | **PASS** | `CREATE INDEX IF NOT EXISTS scope_app_pkg_idx` di `onOpen()` aman untuk database lama dan baru. |
| Gradle CI Build (`./gradlew zipAll`) | — | **PENDING CI RUN** | Menunggu push ke `regarflowl-netizen/JessicaLS` (workflow `Core` pada `ubuntu-24.04`). |

---

## 2. Checklist Regresi Perangkat (Status: NOT TESTED — Menunggu Build CI & Uji Perangkat)

- [ ] **Zygisk initialization & Daemon startup:** `jessicalsd` aktif tanpa crash/bootloop.
- [ ] **Binder handshake & Parasitic Manager:** `Constants.setBinder(IBinder)` sukses dan layar Home menampilkan status `Active`.
- [ ] **Module discovery (install/update/remove):** Modul baru muncul setelah `debounce(250)` tanpa duplikasi baris dan tanpa perlu reboot/refresh manual.
- [ ] **Module enable/disable & Scope editor:** Toggle dan simpan scope bekerja normal, ikon preview scope tampil, dan bertahan setelah reboot.
- [ ] **Legacy Xposed & libxposed API 101 hooking:** Minimal 1 modul legacy dan 1 modul API 101 benar-benar termuat dan melakukan hook pada proses target.
- [ ] **Resource hooking & Restart framework:** Tidak ada regresi pada `XResources` maupun restart daemon.
- [ ] **Uninstall safety:** `/data/adb/lspd` tetap utuh saat modul di-uninstall.
