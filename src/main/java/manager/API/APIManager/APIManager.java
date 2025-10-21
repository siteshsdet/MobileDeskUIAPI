package manager.API.APIManager;

import configurations.Driver.DriverManager;
import configurations.Util.PropertyConfig;
import configurations.Util.PropertyUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

public class APIManager {

    /**
     * @keyword Using volatile keyword get the proper and updated value for all threads.
     */
    private volatile static APIManager apiManager;

    /**
     * @implNote Restricted user to create a copy of this constructor
     * @Throws IOException
     */
    private APIManager() throws IOException {
        if (Objects.nonNull(apiManager)) {
            throw new RuntimeException(PropertyUtil.propertyInstance()
                    .getPropertyData().getProperty(PropertyConfig.CANNOT_CREATE_COPY_OBJECT.toString())
                    + apiManager.getClass().getSimpleName());        }
    }

    /**
     * @implNote Get the APIManager instance
     * @return apiManager
     * @throws IOException
     */
    public static APIManager getAPIManager() throws IOException {
        if (Objects.isNull(apiManager)) {
            synchronized (APIManager.class) {
                if (Objects.isNull(apiManager)) {
                    apiManager = new APIManager();
                }
            }
        }
        return apiManager;
    }

    /**
     * @implNote Get the authentication tokem from DevTool
     * @param key
     * @return answer [boolean]
     * @throws IOException
     */
    public boolean getAccessToken(String key) throws IOException {
        boolean answer = false;
        WebDriver driver = DriverManager.driverInstance().getDriver("web");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(_driver -> js.executeScript("return window.sessionStorage.getItem('csa-tabbed-browsing');") != null);
        String chatLoadedValue = (String) js.executeScript("return window.sessionStorage.getItem('csa-tabbed-browsing');");
        System.out.println("Complete Key and values : " + chatLoadedValue);
        String[] ApplicationTabData = chatLoadedValue.split(",");
        String[] getKeyValue = null;
        try {
            if (key.equalsIgnoreCase("pid")) {
                getKeyValue = ApplicationTabData[0].replaceAll("\"", "").split(":");
                if(getKeyValue[0].contains(key)) System.out.println("Session Storage " + key + " Value : " + getKeyValue[1]);
                answer = true;
            } else if (key.equalsIgnoreCase("tid")) {
                getKeyValue = ApplicationTabData[1].replaceAll("\"", "").split(":");
                if(getKeyValue[0].contains(key)) System.out.println("Session Storage " + key + " Value : " + getKeyValue[1]);
                answer = true;
            } else if (key.equalsIgnoreCase("rid")) {
                getKeyValue = ApplicationTabData[2].replaceAll("\"", "").split(":");
                if(getKeyValue[1].contains(key)) System.out.println("Session Storage " + key + " Value : " + getKeyValue[2]);
                answer = true;
            }
            else System.out.println("This key : "+key+", is not present");
        } catch (Exception exception) {
            answer = false;
            throw new RuntimeException();
        } finally {
            DriverManager.driverInstance().stopDriver("web");
        }
        return answer;
    }
}

