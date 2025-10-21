package manager.Mobiles;

import configurations.Util.PropertyConfig;
import configurations.Util.PropertyUtil;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.time.Duration;

public final class CapabilitiesManager {

    /**
     * @keyword Using volatile keyword get the proper and refreshed value for all threads.
     */
    private volatile static CapabilitiesManager capabilitiesManager;

    /**
     * Restricted user to create a copy of this constructor
     *
     * @Throws IOException
     */
    private CapabilitiesManager() throws IOException {
        if (capabilitiesManager != null) {
            throw new RuntimeException(PropertyUtil.propertyInstance()
                    .getPropertyData().getProperty(PropertyConfig.CANNOT_CREATE_COPY_OBJECT.toString())
                    + capabilitiesManager.getClass().getSimpleName());
        }
    }

    /**
     * @return capabilitiesManager
     * @implNote Double check code for driver is initiated or not.
     */
    public static CapabilitiesManager capabilityInstance() throws IOException {

        if (capabilitiesManager == null) {
            synchronized (CapabilitiesManager.class) {
                if (capabilitiesManager == null) {
                    capabilitiesManager = new CapabilitiesManager();
                }
            }
        }
        return capabilitiesManager;
    }

    /**
     * @Initialization UiAutomator2Options
     * @method Method to initiate the Mobile Android driver session.
     */
    public UiAutomator2Options getAndroidCapabilities() throws IOException {
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        try {
            uiAutomator2Options.setPlatformName(PropertyConfig.ANDROID_PLATFORM.toString())
                    .setPlatformVersion(PropertyConfig.ANDROID_PLATFORM_VERSION.toString())
                    .setUdid(PropertyConfig.ANDROID_UDID.toString())
                    .setDeviceName(PropertyConfig.ANDROID_DEVICENAME.toString())
                    .setAppPackage(PropertyConfig.APP_PACKAGE.toString())
                    .setAppActivity(PropertyConfig.APP_ACTIVITY.toString())
                    .setAutomationName(PropertyConfig.ANDROID_AUTOMATION_NAME.toString())
                    .setChromeOptions(ImmutableMap.of((PropertyConfig.CHROME_OPTION.toString()), true))
                    .setAutoGrantPermissions(true)
                    .setAppWaitForLaunch(true)
                    .setAvdLaunchTimeout(Duration.ofSeconds(5000))
                    .setApp(PropertyConfig.ANDROID_APK.toString())
                    .setCapability("unicodeKeyboard", true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return uiAutomator2Options;
    }

    /**
     * @Initialization XCUITestOptions
     * @method Method to initiate the Mobile iOS driver session.
     */
    public XCUITestOptions getIOSCapabilities() throws IOException {
        XCUITestOptions xcuiTestOptions = new XCUITestOptions();
        try {
            xcuiTestOptions.setPlatformVersion(PropertyConfig.IOS_PLATFORM_VERSION.toString())
                    .setAutomationName(PropertyConfig.IOS_AUTOMATION_NAME.toString())
                    .setDeviceName(PropertyConfig.IOS_DEVICENAME.toString())
                    .setApp(PropertyConfig.ISO_APP_PATH.toString())
                    //.setUdid(PropertyConfig.IOS_UDID_NAME.toString())
                    //.setBundleId(PropertyConfig.IOS_BUNDLE_ID.toString())
                    .setNewCommandTimeout(Duration.ofSeconds(10))
                    .setCapability("shouldTerminateApp", true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return xcuiTestOptions;
    }
}



