package Steps.TensorFlow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

public class SampleTest {
    @Test
    public void sampleTest() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        boolean testPassed = false;

        try {
            driver.get("https://example.com");
            String title = driver.getTitle();
            Assert.assertNotNull(title);
            testPassed = title.contains("Example");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            writeResultToCSV("sampleTest", testPassed ? "PASS" : "FAIL");
        }
    }

    public void writeResultToCSV(String testName, String result) {
        try (FileWriter writer = new FileWriter("test_results.csv", true)) {
            writer.append(testName).append(",").append(result).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
