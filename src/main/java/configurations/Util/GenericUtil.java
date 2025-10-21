package configurations.Util;

import configurations.Driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.ResourceLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GenericUtil {

    /**
     * @keyword Using volatile keyword gets the proper and refreshed value for all threads.
     */
    private volatile static GenericUtil genericUtil;

    /**
     * Restricted user to create a copy of this constructor
     *
     * @Throws IOException
     */
    private GenericUtil() throws IOException {
        if (genericUtil != null) {
            throw new RuntimeException(PropertyUtil.propertyInstance()
                    .getPropertyData().getProperty(PropertyConfig.CANNOT_CREATE_COPY_OBJECT.toString())
                    + genericUtil.getClass().getSimpleName());
        }
    }

    /**
     * @implNote Singlton Class -> Double check code for androidMobileUtils is initiated or not.
     */
    public static GenericUtil genericUtilInstance() throws IOException {

        if (genericUtil == null) {
            synchronized (GenericUtil.class) {
                if (genericUtil == null) {
                    genericUtil = new GenericUtil();
                }
            }
        }
        return genericUtil;
    }

    /**
     * @implNote Get the locator for a single element
     * @param driverType
     * @param locatorType
     * @param locatorValue
     * @return answer [boolean]
     */
    public static WebElement getElementByLocator(@Optional String driverType, String locatorType, String locatorValue) throws IOException {
        switch (driverType.toLowerCase()) {
            case "web" -> {
                WebDriver driver = DriverManager.driverInstance().getDriver("web");
                By by = switch (locatorType.toLowerCase()) {
                    case "id" -> By.id(locatorValue);
                    case "name" -> By.name(locatorValue);
                    case "classname", "class" -> By.className(locatorValue);
                    case "tagname", "tag" -> By.tagName(locatorValue);
                    case "css", "cssselector" -> By.cssSelector(locatorValue);
                    case "xpath" -> By.xpath(locatorValue);
                    case "linktext" -> By.linkText(locatorValue);
                    case "partiallinktext" -> By.partialLinkText(locatorValue);
                    default -> throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
                };
                return driver.findElement(by);
            }
            case "mobile" -> {
                AppiumDriver driver = (AppiumDriver) DriverManager.driverInstance().getDriver("mobile");
                By by = switch (locatorType.toLowerCase()) {
                    case "id" -> By.id(locatorValue);
                    case "name" -> By.name(locatorValue);
                    case "classname", "class" -> By.className(locatorValue);
                    case "tagname", "tag" -> By.tagName(locatorValue);
                    case "css", "cssselector" -> By.cssSelector(locatorValue);
                    case "xpath" -> By.xpath(locatorValue);
                    case "linktext" -> By.linkText(locatorValue);
                    case "partiallinktext" -> By.partialLinkText(locatorValue);
                    default -> throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
                };
                return driver.findElement(by);
            }
            default -> throw new IllegalArgumentException("Unsupported driver type: " + driverType);
        }
    }

    /**
     * @implNote Get the locator for a single element
     * @param driverType
     * @param locatorType
     * @param locatorValue
     * @return answer [boolean]
     */
    public static List<WebElement> getElementByLocators(String driverType, String locatorType, String locatorValue) throws IOException {
        switch (driverType){
            case "web" -> {
                WebDriver driver = DriverManager.driverInstance().getDriver("web");
                By by = switch (locatorType.toLowerCase()) {
                    case "id" -> By.id(locatorValue);
                    case "name" -> By.name(locatorValue);
                    case "classname", "class" -> By.className(locatorValue);
                    case "tagname", "tag" -> By.tagName(locatorValue);
                    case "css", "cssselector" -> By.cssSelector(locatorValue);
                    case "xpath" -> By.xpath(locatorValue);
                    case "linktext" -> By.linkText(locatorValue);
                    case "partiallinktext" -> By.partialLinkText(locatorValue);
                    default -> throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
                };
                return driver.findElements(by);
            }
            case "mobile" -> {
                AppiumDriver driver = (AppiumDriver) DriverManager.driverInstance().getDriver("mobile");
                By by = switch (locatorType.toLowerCase()) {
                    case "id" -> By.id(locatorValue);
                    case "name" -> By.name(locatorValue);
                    case "classname", "class" -> By.className(locatorValue);
                    case "tagname", "tag" -> By.tagName(locatorValue);
                    case "css", "cssselector" -> By.cssSelector(locatorValue);
                    case "xpath" -> By.xpath(locatorValue);
                    case "linktext" -> By.linkText(locatorValue);
                    case "partiallinktext" -> By.partialLinkText(locatorValue);
                    default -> throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
                };
                return driver.findElements(by);
            }
        }
        return List.of();
    }

    /**
     * @param driverType
     * @param locatorType
     * @param locatorValue
     * @param attributeName
     * @return answer [boolean]
     * @throws IOException
     */
    public static String getAttributeByLocator(String driverType, String locatorType, String locatorValue, String attributeName) throws IOException {
        switch (driverType.toLowerCase()) {
            case "web", "mobile" -> {
                return getElementByLocator(driverType, locatorType, locatorValue).getAttribute(attributeName);
            }
            default -> throw new IllegalArgumentException("Unsupported driver type: " + driverType);
        }
    }
    public static boolean containsText(String text, String expectedText) {
        boolean answer = false;
        if (!(text.isEmpty() || expectedText.isEmpty())) {
            try {
                text.contains(expectedText);
                answer = true;
            } catch (Exception e) {
                answer = false;
            }
        }
        return answer;
    }

    /**
     * @param locatorType
     * @param locatorValue
     * @return answer [boolean]
     * @throws IOException
     */
    public static boolean slide(String locatorType, String locatorValue) throws IOException {
        WebDriver driver = DriverManager.driverInstance().getDriver("web");
        boolean answer = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.left='60%';", getElementByLocator("web", locatorType, locatorValue));
            js.executeScript("$('#slider').slider('value', 60).trigger('slidechange');");
            answer = true;
        } catch (IOException e) {
            answer = false;
        }
        return answer;
    }


    /**
     * @implNote Perform mouse hover action on the target element
     * @param driverType
     * @param locatorType
     * @param locatorValue
     * @param string
     * @return answer [boolean]
     * @throws IOException
     */
    public static boolean mouseHover(String driverType, String locatorType, String locatorValue, String... string) throws IOException {
        WebDriver driver = DriverManager.driverInstance().getDriver("web");
        boolean answer = false;
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(getElementByLocator(driverType, locatorType, locatorValue)).perform();
            answer = true;
        } catch (Exception e) {
            answer = false;
        }
        return answer;
    }

    /**
     * @implNote Perform click operation using actions
     * @param driverType
     * @param locatorType
     * @param locatorValue
     * @param string
     * @return answer [boolean]
     * @throws IOException
     */
    public static boolean actionClick(String driverType, String locatorType, String locatorValue, String... string) throws IOException {
        WebDriver driver = DriverManager.driverInstance().getDriver("web");
        boolean answer = false;
        try {
            Actions actions = new Actions(driver);
            actions.click(getElementByLocator(driverType, locatorType, locatorValue)).perform();
            answer = true;
        } catch (Exception e) {
            answer = false;
        }
        return answer;
    }

    /**
     * @implNote Perform click operation using actions
     * @param driverType
     * @param locatorType
     * @param locatorValue
     * @param string
     * @return answer [boolean]
     * @throws IOException
     */
    public static boolean click(String driverType, String locatorType, String locatorValue, String... string) {
        boolean answer = false;
        try {
            getElementByLocator(driverType, locatorType, locatorValue).click();
            answer = true;
        } catch (Exception e) {
            answer = false;
        }
        return answer;
    }

    /**
     * @implNote Perform sleep action to wait statically for any operation
     * @param seconds
     */
    public static boolean sleep(double seconds) throws IOException {
        boolean answer = false;
        try {
            long millis = (long) (seconds * 1000);
            Thread.sleep(millis);
            answer = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            GenericUtil.genericUtilInstance().getLog().info("Interrupted to perform sleep action");
            answer = false;
        }
        return answer;
    }


    /**
     * @param filePath
     * @return inputStream
     * @Throws IOException
     * @method Getting the resource loaded
     */
    public InputStream getResources(String filePath) {
        InputStream inputStream;
        try {
            inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(filePath);
            if (Objects.nonNull(inputStream)) {
                return inputStream;
            }
            return Files.newInputStream(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("FileConstants.FILE_NOT_LOADED_ERROR_MESSAGE");
        }
    }

    /**
     * @return LogManager
     * @method Getting log to get the info, error and debug
     */
    public Logger getLog() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    /**
     * @implNote Handle context native and webview
     * @return answer [boolean]
     */
    public boolean handleContext() {
        boolean answer = true;
        try {
            SupportsContextSwitching contextDriver = (SupportsContextSwitching) DriverManager.driverInstance().getDriver("mobile");
            Set<String> contextNames = contextDriver.getContextHandles();
            for (String contextName : contextNames) {
                GenericUtil.genericUtilInstance().getLog().info("<====== Available contexts are : {}=======> ", contextNames);
                if (contextName.contains("WEB_VIEW")) {
                    contextDriver.context("WEB_VIEW");
                } else {
                    contextDriver.context("NATIVE_APP");
                }
            }
        } catch (Exception e) {
            answer = false;
            throw new RuntimeException(e);
        }
        return answer;
    }

    /**
     * @implNote Launches the Android emulator with the given AVD name and waits until it's ready.
     * @param avdName The name of the AVD to launch (e.g., "Pixel_8_Pro")
     */
    public boolean turnOnEmulator(String avdName, String emulatorPath) throws IOException {
        boolean answer = true;
        try {
            GenericUtil.genericUtilInstance().getLog().info(PropertyConfig.EMULATOR_STARTING);
            ProcessBuilder pb = new ProcessBuilder(emulatorPath, "-avd", avdName);
            pb.redirectErrorStream(true);
            pb.start();
            GenericUtil.genericUtilInstance().getLog().info(PropertyConfig.EMULATOR_STARED);
            Thread.sleep(5000);
        } catch (IOException | InterruptedException exception) {
            GenericUtil.genericUtilInstance().getLog().info(PropertyConfig.EMULATOR_STARTING_FAIL);
            answer = false;
        }
        return answer;
    }

    /**
     * @implNote Stop the running emulator based on emulator-id
     * @param emulatorId
     * @return answer [boolean]
     * @throws IOException
     */
    public boolean stopEmulator(String emulatorId) throws IOException {
        boolean answer = false;
        try {
            GenericUtil.genericUtilInstance().getLog().info(PropertyConfig.EMULATOR_STOPING);
            ProcessBuilder pb = new ProcessBuilder("adb", "-s", emulatorId, "emu", "kill");
            pb.redirectErrorStream(true);
            pb.start();
            answer = true;
            GenericUtil.genericUtilInstance().getLog().info(PropertyConfig.EMULATOR_STOPED);
        } catch (IOException e) {
            GenericUtil.genericUtilInstance().getLog().info(PropertyConfig.EMULATOR_STOPING_FAIL);
            answer = false;
        }
        return answer;
    }

    /**
     * @param scenario Scenario
     * @implNote Taking screenshot for the failed test cases
     * @Throws IOException
     */
    public static void takingScreenShotForTheMobileFailedTestCases(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.driverInstance().getDriver("mobile"))
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, PropertyUtil.propertyInstance().getPropertyData().getProperty(PropertyConfig.SCREENSHOT_IMAGE_TYPE.toString()), scenario.getName());
            GenericUtil.genericUtilInstance().getLog().error(PropertyUtil
                    .propertyInstance().getPropertyData().getProperty(PropertyConfig.SUCCESSFUL_SCREENSHOT.toString()), new Exception(PropertyUtil
                    .propertyInstance().getPropertyData().getProperty(PropertyConfig.THIS_STEP_FAILED.toString())));
        }
    }

    public boolean openWord(String filePath) {
        boolean answer = false;
        try {
            File file = new File(filePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
                GenericUtil.genericUtilInstance().getLog().info("File opened successfully, with path : {}", filePath);
                answer = true;
            } else {
                GenericUtil.genericUtilInstance().getLog().info("Unable to open file, with path : {}", filePath);
            }
        } catch (Exception e) {
            answer = false;
        }
        return answer;
    }

    public boolean validateWordContent(String filePath, String expectedText) {
        boolean answer = false;
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                if (para.getText().contains(expectedText)) {
                    System.out.println("✅ Found expected content: " + expectedText);
                    answer = true;
                }
            }
            System.out.println("❌ Expected content not found.");
        } catch (Exception e) {
           answer = false;
        }
        return answer;
    }

    public boolean quitMacApp(String appName) throws IOException {
        try {
            List<String> command = Arrays.asList(
                    "osascript",
                    "-e",
                    "tell application \"" + appName + "\" to quit"
            );
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                GenericUtil.genericUtilInstance().getLog().info("Successfully quit app: {}", appName);
                return true;
            } else {
                GenericUtil.genericUtilInstance().getLog().warn("osascript returned non-zero exit code for app: {}", appName);
                return false;
            }
        } catch (Exception e) {
            GenericUtil.genericUtilInstance().getLog().error("Failed to quit app: {}", appName, e);
            return false;
        }
    }


    /**
     * @implNote Get read of PDF content based on column and sum
     * @param pdfFilePath
     * @param columnName
     * @return answer [boolean]
     */
    public boolean getSumPDFBasedOnColumnName(String pdfFilePath, String columnName){
        boolean answer = false;
        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            PDFTextStripper textStripper = new PDFTextStripper();
            String pdfText = textStripper.getText(document);

            String regex = ""+columnName+"(?:\\s|\\r|\\n|,|\\.)*?(\\d+(?:\\.\\d{1,2})?)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(pdfText);

            double totalDepositedCash = 0.0;
            System.out.println("=========== '"+columnName+"' =========== ");

            while (matcher.find()) {
                String amountString = matcher.group(1);

                if (amountString != null && !amountString.trim().isEmpty()) {
                    try {
                        double amount = Double.parseDouble(amountString);
                        totalDepositedCash += amount;
                        System.out.println(amount);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid amount: " + amountString);
                    }
                }
            }
            document.close();
            System.out.printf("Total "+columnName+" : %.2f%n", totalDepositedCash);
            answer = true;
        } catch (IOException e) {
            answer = false;
            System.out.println(e.getLocalizedMessage());
        }
        return answer;
    }
}
