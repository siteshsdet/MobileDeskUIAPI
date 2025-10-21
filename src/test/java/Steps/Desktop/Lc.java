package Steps.Desktop;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.mac.options.Mac2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;

public class Lc {
    private static AppiumDriver driver;

    public static void main(String[] args) {
        try {
            Mac2Options options = new Mac2Options();
            options.setPlatformName("mac");
            options.setAutomationName("mac2");
            options.setCapability("bundleId", "com.apple.calculator");
            AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);


            clickButton("1");
            clickButton("2");
            clickButton("+");
            clickButton("3");
            clickButton("=");

            WebElement result = driver.findElement(By.xpath("//XCUIElementTypeStaticText"));
            System.out.println("Result: " + result.getText());

        } catch (MalformedURLException e) {
            System.err.println("Invalid Appium server URL");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void clickButton(String label) {
        try {
            WebElement button = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='" + label + "']"));
            button.click();
            Thread.sleep(500);
        } catch (Exception e) {
            System.err.println("Failed to click button: " + label);
        }
    }
}
