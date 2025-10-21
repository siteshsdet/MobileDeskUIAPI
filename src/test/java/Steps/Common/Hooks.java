package Steps.Common;

import configurations.Driver.DriverManager;
import configurations.Util.GenericUtil;
import configurations.Util.PropertyConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class Hooks {

    /**
     * @implNote Start the appium session based on platform
     */
    @Before
    public void startSession(Scenario scenario) throws IOException {
        String platformName = System.getProperty("platformName", "Android");
        String deviceType = System.getProperty("deviceType", "virtual");

        GenericUtil.genericUtilInstance().getLog().info("Starting scenario: {}", scenario.getName());
        GenericUtil.genericUtilInstance().getLog().info("Platform: {}, Device: {}", platformName, deviceType);

        try {
            switch (platformName) {
                case "Android":
                    if (deviceType.equalsIgnoreCase(PropertyConfig.VIRTAUL_DEVICE.toString())) {
                        GenericUtil.genericUtilInstance().turnOnEmulator(PropertyConfig.EMULATOR_AVD_NAME.toString(), PropertyConfig.EMULATOR_PATH.toString());
                    }
                    DriverManager.driverInstance().initializeDriver("mobile", "Android", PropertyConfig.ANDROID_PLATFORM.toString());
                    break;
                case "iOS":
                    if (deviceType.equalsIgnoreCase(PropertyConfig.VIRTAUL_DEVICE.toString())) {
                        GenericUtil.genericUtilInstance().getLog().info("iOS simulator setup");
                    }
                    DriverManager.driverInstance().initializeDriver("mobile", "iOS", PropertyConfig.ANDROID_PLATFORM.toString());
                    break;
                default:
                    GenericUtil.genericUtilInstance().getLog().info("Platform name is wrong!!");
            }
            GenericUtil.genericUtilInstance().handleContext();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void stopSession(Scenario scenario) {
        try {
            Thread.sleep(5000);
            if (DriverManager.driverInstance().getDriver("mobile") != null) {
                DriverManager.driverInstance().stopDriver("mobile");
                GenericUtil.genericUtilInstance().getLog().info("Mobile driver was running and it stopped now!");
            } else {
                DriverManager.driverInstance().stopDriver("web");
                GenericUtil.genericUtilInstance().getLog().info("Web driver was running and it stopped now!");
            }
            GenericUtil.genericUtilInstance().stopEmulator(PropertyConfig.EMULATOR_UDID.toString());
            Runtime.getRuntime().exec("xcrun simctl shutdown booted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterStep
    public void takingScreenShotFailedStep(Scenario scenario) throws Exception {
        GenericUtil.takingScreenShotForTheMobileFailedTestCases(scenario);
    }
}



