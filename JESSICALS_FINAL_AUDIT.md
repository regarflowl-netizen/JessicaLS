# JessicaLS Final Audit & Post-Remediation Verification Report

## 1. Perbaikan Masalah Kritis & Temuan Audit (Remediation)

Seluruh perbaikan di bawah ini diterapkan **tanpa mengubah** engine LSPlant, Dobby, LSPlt, Zygisk native injection, maupun mekanisme hooking yang sudah konsisten:

| ID | Tingkat | Masalah Audit | Perbaikan Diterapkan | Status |
|---|---|---|---|---|
| **P0-1** | **Release Blocker** | `zygisk/module/uninstall.sh` menjalankan `rm -rf /data/adb/lspd` yang dapat menghapus konfigurasi & database pengguna. | Diubah agar hanya membersihkan file runtime sementara (`rm -f /data/adb/lspd/.cli_sock /data/adb/lspd/lock`) dan **mempertahankan** `/data/adb/lspd` secara default. Lolos `sh -n`. | **FIXED** |
| **P0-2** | **Release Blocker** | `GitHubRepository.kt` masih menargetkan `JingMatrix/Vector` dengan `ZIP_PREFIX = "Vector-"`, berisiko menawarkan ZIP Vector upstream ke installer root. | Identitas dipisahkan: `UPSTREAM_REPO = "JingMatrix/Vector"` (atribusi) vs `OWNER = "regarflowl-netizen"`, `REPO = "regarflowl-netizen/JessicaLS"`, dan `ZIP_PREFIX = "JessicaLS-"` yang juga ditegakkan pada filter aset `frameworkReleases()`. | **FIXED** |
| **P1-1** | High | `.github/workflows/core.yml` masih memakai judul rilis `Vector ${TAG}` / `Vector ... canary` dan kondisi skip `Release Vector`. | Seluruh judul rilis/canary dan kondisi skip commit diperbarui ke `JessicaLS` / `Release JessicaLS`. | **FIXED** |
| **P1-2** | High | Arsip ZIP sumber tidak menyertakan `.git` root (110 MB) sehingga build dari ZIP saja akan jatuh ke versi fallback (`1.0`, `1`, `unknown`). | Didokumentasikan secara eksplisit: build rilis resmi harus dijalankan dari clone Git rekursif (atau push ke repo GitHub `regarflowl-netizen/JessicaLS` + GitHub Actions) agar metadata versi & tag Git terbaca akurat. | **DOCUMENTED** |
| **P1-3** | High | File `zygisk/update.json` yatim masih mengarah ke URL ZIP Vector v2.2 upstream. | File `zygisk/update.json` dihapus karena `module.prop` memang tidak memakai `updateJson` dan Manager memakai `GitHubRepository`. | **FIXED** |
| **P2-1** | Medium | `Cli.kt` masih menampilkan `vector-cli` / `Vector CLI` dan `ModulesScreen.kt` memakai `vector-modules.bak`. | Diperbarui ke `jessicals-cli`, `JessicaLS CLI`, `JessicaLSIPC`, dan `jessicals-modules.bak`. | **FIXED** |
| **P2-2** | Medium | 26 string terjemahan berimbuhan (`de`, `ko`, `pl`, `et`) di `manager`, `daemon`, dan `manager-ui` masih memuat kata `Vector`. | Seluruh 7 file XML diperbarui dan divalidasi dengan parser XML; sisa `Vector` pada nilai string seluruh locale kini **0**. | **FIXED** |
| **P2-3** | Medium | README modul (`zygisk`, `daemon`, `manager`, `legacy`, `dex2oat`, `xposed`) masih menyebut identifier lama. | Seluruh README modul diperbarui agar sesuai struktur kode aktual JessicaLS sambil mempertahankan atribusi upstream. | **FIXED** |

## 2. Ringkasan Status Keseluruhan

| Area | Status | Bukti / Keterangan |
|---|---|---|
| Repository audit & Graphify | **PASS** | Base upstream `JingMatrix/Vector` commit `6f6b976e`, 13 modul Gradle, 5.342 node / 261 komunitas AST. |
| Submodules (9 upstream) | **PASS** | Seluruh 9 submodule tetap bersih pada commit pinned upstream (`git submodule status` tanpa modifikasi). |
| Static Integrity & Shell/XML | **PASS** | 37/37 cross-layer checks PASS, 6/6 skrip shell lolos `sh -n`, 0 sisa `Vector` di seluruh nilai `strings*.xml`, 0 sisa identifier aktif di kode (kecuali deteksi konflik `zygisk_vector` di `customize.sh`). |
| Gradle Full Build (`zipAll`) | **BLOCKED (Sandbox Toolchain)** | Lingkungan sandbox tidak memiliki JDK 21 (hanya OpenJDK 17), Android SDK 37 (`buildToolsVersion 37.0.0`), NDK `30.0.16248370`, atau CMake `3.29.8+`. |
| Zygisk ZIP & APK Inspection | **NOT BUILT** | Menunggu eksekusi `./gradlew :manager:assembleDebug` dan `./gradlew :zygisk:zipAll` di host/CI ber-toolchain lengkap. |
| Device Runtime Testing | **NOT TESTED** | Instalasi, reboot, Binder handshake (`setBinder`), Parasitic Manager, modul legacy Xposed & libxposed API 101, logcat, serta upgrade/uninstall menunggu perangkat Android ber-root dengan Zygisk. |

## 3. Langkah Lanjutan untuk Build Penuh & Uji Perangkat

1. Push/import repositori ke GitHub (`regarflowl-netizen/JessicaLS`) atau gunakan clone Git rekursif dengan tag di mesin host yang memiliki **JDK 21, Android SDK 37, Build Tools 37.0.0, NDK `30.0.16248370`, dan CMake `3.29.8+`**.
2. Jalankan build penuh:
   ```bash
   ./gradlew --no-daemon clean
   ./gradlew :manager:assembleDebug
   ./gradlew :zygisk:zipDebug
   ./gradlew :zygisk:zipAll
   ```
3. Verifikasi isi ZIP rilis (`module.prop`, `manager.apk`, `daemon.apk`, `framework/jessicals.dex`, `zygisk/*.so`, `bin/*`, dan seluruh `.sha256`), lalu pasang pada perangkat Android ber-root dengan Zygisk dan pantau log melalui:
   ```bash
   adb logcat -s JessicaLS JessicaLSManager JessicaLSLogcat JessicaLSContext JessicaLSLegacyBridge JessicaLSModuleManager
   ```
