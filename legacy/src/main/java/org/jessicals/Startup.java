package org.jessicals;

import org.jessicals.ipc.IFrameworkService;
import org.jessicals.util.Utils;
import org.jessicals.impl.core.JessicaLSStartup;
import org.jessicals.impl.di.JessicaLSBootstrap;
import org.jessicals.legacy.LegacyDelegateImpl;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedInit;

public class Startup {

    public static void bootstrapXposed(boolean systemServerStarted) {
        try {
            JessicaLSStartup.bootstrap(XposedInit.startsSystemServer, systemServerStarted);
            XposedInit.loadLegacyModules();
        } catch (Throwable t) {
            Utils.logE("Error during framework initialization", t);
        }
    }

    /**
     * Registers a pre-built {@code LoadedApk} (the LSPatch rootless target) with the modern hooks so
     * its package lifecycle is dispatched when its class loader is realized. See
     * {@link JessicaLSStartup#trackLoadedApk(Object)}.
     */
    public static void trackLoadedApk(Object loadedApk) {
        JessicaLSStartup.trackLoadedApk(loadedApk);
    }

    public static void initXposed(boolean isSystem, String processName, String appDir, IFrameworkService service) {
        // Establish the Dependency Injection contract
        JessicaLSBootstrap.INSTANCE.init(new LegacyDelegateImpl());

        // Initialize legacy resources and state
        XposedBridge.initXResources();
        XposedInit.startsSystemServer = isSystem;

        // Hand off execution to the modern framework initialization
        JessicaLSStartup.init(isSystem, processName, appDir, service);
    }
}
