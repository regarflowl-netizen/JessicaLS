package org.jessicals.core

import android.os.IBinder
import android.os.Process
import org.jessicals.ipc.IFrameworkService
import org.jessicals.util.Log
import org.jessicals.util.Utils
import org.jessicals.BuildConfig
import org.jessicals.GrapheneDclHooker
import org.jessicals.ParasiticManagerHooker
import org.jessicals.ParasiticManagerSystemHooker
import org.jessicals.Startup
import org.jessicals.impl.core.JessicaLSServiceClient

/** Main entry point for the Java-side loader, invoked via JNI from the Vector Zygisk module. */
object Main {

    /**
     * Shared initialization logic for both System Server and Application processes.
     *
     * @param isSystem True if this is the system_server process.
     * @param isLateInject True if Zygisk injected us into a system_server that was already
     *   running, which is past the points the hooks would have caught, so the startup they drive
     *   is run by hand instead.
     * @param niceName The process name (e.g., package name or "system").
     * @param appDir The application's data directory.
     * @param binder The Binder token associated with the application service.
     */
    @JvmStatic
    fun forkCommon(
        isSystem: Boolean,
        isLateInject: Boolean,
        niceName: String,
        appDir: String?,
        binder: IBinder,
    ) {
        // Initialize system-specific resolution hooks if in system_server
        if (isSystem) {
            ParasiticManagerSystemHooker.start()
            // Exempt the manager host from GrapheneOS DCL restriction; no-op on other systems.
            GrapheneDclHooker.start()
        }

        // Initialize Xposed bridge components
        val appService = IFrameworkService.Stub.asInterface(binder)
        Startup.initXposed(isSystem, niceName, appDir, appService)

        // Configure logging levels from the service client
        runCatching { Log.muted = JessicaLSServiceClient.isLogMuted }
            .onFailure { t -> Utils.logE("Failed to configure logs from service", t) }

        // Check if this process is the designated Vector Manager.
        if (niceName == BuildConfig.ManagerPackageName) {
            ParasiticManagerHooker.isParasitic = Process.myUid() == BuildConfig.HostPackageUid
            val type = if (ParasiticManagerHooker.isParasitic) "parasitic" else "user-installed"
            if (ParasiticManagerHooker.start()) {
                Utils.logI("Manager ($type) loaded into host, skipping standard bootstrap.")
                return
            }
        }

        // Standard Xposed module loading for third-party apps
        Utils.logV("Loading Vector/Xposed for $niceName (UID: ${Process.myUid()})")
        Startup.bootstrapXposed(isSystem && isLateInject)
    }
}
