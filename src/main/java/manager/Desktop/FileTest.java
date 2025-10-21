package manager.Desktop;

import configurations.Driver.DriverManager;
import configurations.Util.GenericUtil;
import configurations.Util.PropertyConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageAndScreens.WebApp.WebAppPages;

import java.io.IOException;

import static configurations.Util.GenericUtil.*;

public class FileTest {

    @Test
    public void openWordFileAndValidate() {
        try {
            GenericUtil.genericUtilInstance().openWord(PropertyConfig.WORD_FILE_PATH.toString());
            Thread.sleep(5000);
            GenericUtil.genericUtilInstance().validateWordContent(PropertyConfig.WORD_FILE_PATH.toString(), "Raveendra");
            Thread.sleep(5000);
            GenericUtil.genericUtilInstance().quitMacApp("Pages");
            Thread.sleep(5000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void downloadPDFAndValidate() throws Exception {
        DriverManager.driverInstance().initializeDriver("web", "chrome", "https://demo.automationtesting.in/FileDownload.html");
        WebDriver driver = DriverManager.driverInstance().getDriver("web");
        driver.findElement(By.xpath("//div/a[@class='btn btn-primary']")).click();
    }

    @Test
    public void slider() throws Exception {
        boolean answer = false;
        WebAppPages webAppPages = new WebAppPages();
        DriverManager.driverInstance().initializeDriver("web", "chrome", "https://demo.automationtesting.in/FileDownload.html");
        WebDriver driver = DriverManager.driverInstance().getDriver("web");
        answer = mouseHover("web", "xpath", webAppPages.widget);
        answer = sleep(0.5);
        answer = click("web", "xpath", webAppPages.slider);
        answer = sleep(2);
        answer = slide("xpath", webAppPages.sliderValue);
        answer = sleep(5);
        String sliderPercentage = getAttributeByLocator("web", "xpath", webAppPages.sliderValue, "style");
        answer = containsText(sliderPercentage, "60");
        Assert.assertTrue(true, "fail");
        DriverManager.driverInstance().stopDriver("web");
        System.out.println("==================== Passed ================");
    }
}
