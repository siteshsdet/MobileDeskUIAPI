package manager.Mobiles;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Objects;

public final class ServerManager {

    /**
     * Singleton instance for ServerManager
     */
    private static volatile ServerManager serverManager;

    /**
     * Thread-safe Appium server instance per test thread
     */
    private static final ThreadLocal<AppiumDriverLocalService> server = new ThreadLocal<>();

    /**
     * Private constructor to prevent external instantiation
     */
    private ServerManager() {
        if (Objects.nonNull(serverManager)) {
            throw new RuntimeException("Use getServerInstance() to access ServerManager");
        }
    }

    /**
     * Double-checked locking for singleton instance
     */
    public static ServerManager getServerInstance() {
        if (Objects.isNull(serverManager)) {
            synchronized (ServerManager.class) {
                if (Objects.isNull(serverManager)) {
                    serverManager = new ServerManager();
                }
            }
        }
        return serverManager;
    }

    /**
     * Lazily initializes and returns thread-local Appium server
     */
    public AppiumDriverLocalService getServer() {
        if (server.get() == null) {
            AppiumDriverLocalService localService = getAppiumService();
            localService.start();
            server.set(localService);
        }
        return server.get();
    }

    /**
     * Builds a new Appium service with dynamic port and custom arguments
     */
    private AppiumDriverLocalService getAppiumService() {
        return new AppiumServiceBuilder()
                .withArgument(() -> "--allow-insecure", "*:chromedriver_autodownload")
                .withArgument(() -> "--use-plugins", "gestures")
                .withArgument(() -> "--session-override")
                .usingAnyFreePort()
                .build();
    }

    /**
     * Stops the thread-local Appium server if running
     */
    public boolean stopServer() {
        boolean answer = false;
        AppiumDriverLocalService localService = server.get();
        try {
            if (localService != null && localService.isRunning()) {
                localService.stop();
                answer = true;
            }
        } catch (Exception exception) {
            answer = false;
            throw new AppiumServerHasNotBeenStartedLocallyException(exception);
        } finally {
            server.remove();
        }
        return answer;
    }
}
