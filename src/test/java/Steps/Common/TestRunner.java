package Steps.Common;

import configurations.Driver.DriverManager;
import configurations.Util.GenericUtil;
import configurations.Util.PropertyConfig;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import manager.Mobiles.ServerManager;
import org.testng.annotations.*;

@CucumberOptions(features = {"src/test/resources/Features/Mobiles"},
glue = "Steps")
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * @implNote Run cucmber-scenarios parallel
     * @return scenarios
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    /**
     * @implNote start the Appium server
     */
    @BeforeSuite
    public static void startServer() {
        try {
            ServerManager.getServerInstance().getServer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @implNote Stop Appium server
     */
    @AfterSuite
    public static void stopServer() {
        try {
            GenericUtil.genericUtilInstance().getLog().info("Stpping Appium Server!!");
            ServerManager.getServerInstance().stopServer();
            GenericUtil.genericUtilInstance().getLog().info("Stpped Appium Server!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


