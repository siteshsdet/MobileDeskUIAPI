package pageAndScreens.mobiles;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;

public class SauceLabsLoginScreen {

    public SauceLabsLoginScreen(AppiumDriver appiumDriver){
        PageFactory.initElements(appiumDriver, this);
    }

    public String getUsernameXpath(String platformName) {
        return switch (platformName.toLowerCase()) {
            case "android" -> "//android.widget.EditText[@content-desc='test-Username']";
            case "ios" -> "//XCUIElementTypeTextField[@name='test-Username']";
            default -> throw new IllegalArgumentException("Unsupported platform: " + platformName);
        };
    }

    public String getPasswordXpath(String platformName) {
        return switch (platformName.toLowerCase()) {
            case "android" -> "//android.widget.EditText[@content-desc='test-Password']";
            case "ios" -> "//XCUIElementTypeStaticText[@name='test-Password']";
            default -> throw new IllegalArgumentException("Unsupported platform: " + platformName);
        };
    }

    public String getLoginButtonXpath(String platformName) {
        return switch (platformName.toLowerCase()) {
            case "android" -> "//android.widget.TextView[@text='LOGIN']";
            case "ios" -> "//XCUIElementTypeStaticText[@name='LOGIN']";
            default -> throw new IllegalArgumentException("Unsupported platform: " + platformName);
        };
    }
}
