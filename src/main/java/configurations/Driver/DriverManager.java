package configurations.Driver;

import configurations.Util.GenericUtil;
import configurations.Util.PropertyConfig;
import configurations.Util.PropertyUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import manager.Mobiles.CapabilitiesManager;
import manager.Mobiles.ServerManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

public class DriverManager {

    /**
     * @keyword Using volatile keyword get the proper and updated value for all threads.
     */
    private volatile static DriverManager driverManager;

    /**
     * @keyword Thread-safe and local driver get for respective drivers
     */
    private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    /**
     * Restricted user to create a copy of this constructor
     * @Throws IOException
     */
    private DriverManager() throws IOException {
        if (driverManager != null) {
            throw new RuntimeException(PropertyUtil.propertyInstance()
                    .getPropertyData().getProperty(PropertyConfig.CANNOT_CREATE_COPY_OBJECT.toString())
                    + driverManager.getClass().getSimpleName());
        }
    }

    /**
     * @return Get the driver instance
     * @throws IOException
     */
    public static DriverManager driverInstance() throws IOException {

        if (driverManager == null) {
            synchronized (DriverManager.class) {
                if (driverManager == null) {
                    driverManager = new DriverManager();
                }
            }
        }
        return driverManager;
    }

    /**
     * @implNote Initialize the driver based on plaform and driver type
     * @param driverType
     * @param platformOrBrowser
     * @param baseURL
     * @return answer [boolean]
     * @throws Exception
     */
    @BeforeClass
    public boolean initializeDriver(@Optional String driverType, @Optional("chrome") String platformOrBrowser, @Optional() String baseURL) throws Exception {
        boolean answer = false;
        try {
            switch (driverType) {
                case "mobile":
                    if (appiumDriver.get() == null) {
                        if (platformOrBrowser.equalsIgnoreCase("Android")) {
                            appiumDriver.set(new AndroidDriver(
                                    ServerManager.getServerInstance().getServer().getUrl(),
                                    CapabilitiesManager.capabilityInstance().getAndroidCapabilities()));
                        } else if (platformOrBrowser.equalsIgnoreCase("iOS")) {
                            appiumDriver.set(new IOSDriver(
                                    ServerManager.getServerInstance().getServer().getUrl(),
                                    CapabilitiesManager.capabilityInstance().getIOSCapabilities()));
                        } else {
                            throw new RuntimeException("Invalid mobile platform: " + platformOrBrowser);
                        }
                        GenericUtil.genericUtilInstance().getLog().info("Mobile driver initialized for: {}", platformOrBrowser);
                        answer = true;
                    }
                    break;

                case "web":
                    if (webDriver.get() == null) {
                        switch (platformOrBrowser) {
                            case "chrome":
                                webDriver.set(new ChromeDriver());
                                break;
                            case "firefox":
                                webDriver.set(new FirefoxDriver());
                                break;
                            case "edge":
                                webDriver.set(new EdgeDriver());
                                break;
                            default:
                                webDriver.set(new ChromeDriver());
                                GenericUtil.genericUtilInstance().getLog().info("Invalid browser name, defaulting to Chrome");
                        }
                        webDriver.get().manage().window().maximize();
                        webDriver.get().get(baseURL);
                        webDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                        GenericUtil.genericUtilInstance().getLog().info("Web driver initialized for: {}", platformOrBrowser);
                        answer = true;
                    }
                    break;

                default:
                    throw new RuntimeException("Unsupported driver type: " + driverType);
            }
        } catch (IOException e) {
            GenericUtil.genericUtilInstance().getLog().error("Driver initialization failed", e);
            throw new RuntimeException(e);
        }
        return answer;
    }

    /**
     * @implNote Get the driver based on case matches
     * @param driverType
     * @return appiumDriver
     */
    public WebDriver getDriver(String driverType) throws IOException {
        switch (driverType.toLowerCase()) {
            case "web":
                if (Objects.nonNull(webDriver.get())) {
                    return webDriver.get();
                } else {
                    return null;
                }
            case "mobile":
                if (Objects.nonNull(appiumDriver.get())) {
                    return appiumDriver.get();
                } else {
                    return null;
                }
            default:
                throw new IllegalArgumentException("Invalid driver type: " + driverType);
        }
    }

    /**
     * @implNote Stop the running-driver based on case matches for
     * @param driverType
     * @return answer [boolean]
     * @throws IOException
     */
    public boolean stopDriver(String driverType) throws IOException {
        boolean answer = false;
        try {
            switch (driverType.toLowerCase()) {
                case "web":
                    if (Objects.nonNull(webDriver.get())) {
                        webDriver.get().quit();
                        webDriver.remove();
                        answer = true;
                    } else {
                        throw new RuntimeException("WebDriver is not initialized");
                    }
                    break;
                case "mobile":
                    if (Objects.nonNull(appiumDriver.get())) {
                        appiumDriver.get().quit();
                        appiumDriver.remove();
                        answer = true;
                    } else {
                        throw new RuntimeException("AppiumDriver is not initialized");
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Invalid driver type: " + driverType);
            }
        } catch (Exception e) {
            GenericUtil.genericUtilInstance().getLog().error("Failed to stop driver: {}", driverType, e);
            throw new RuntimeException("Driver shutdown failed", e);
        }
        return answer;
    }
}
