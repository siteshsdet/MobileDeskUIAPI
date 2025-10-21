package configurations.Util;

public enum PropertyConfig {

    /**
     * @Android
     * Android Mobile Device specifications
     */
    ANDROID_PLATFORM("Android"),
    REAL_DEVICE("real"),
    VIRTAUL_DEVICE("virtual"),
    ANDROID_UDID("emulator-5554"),
    //ANDROID_UDID("RZCX61YB3SF"),
    ANDROID_DEVICENAME("Pixel_8_Pro"),
    // ANDROID_DEVICENAME("Galaxy S24"),
    ANDROID_PLATFORM_VERSION("16"),
    APP_PACKAGE("com.saucelabs.mydemoapp.rn"),
    APP_ACTIVITY("com.saucelabs.mydemoapp.rn.MainActivity"),
    ANDROID_AUTOMATION_NAME("UiAutomator2"),
    ANDROID_APK("/Users/siteshkumarvishwakarma/ME/Stuffs/MobileApps/Android-Demo-App.apk"),
    CHROME_OPTION("chromeOptions"),

    /**
     * @Emulators
     */
    EMULATOR_PATH("/Users/siteshkumarvishwakarma/Library/Android/sdk/emulator/emulator"),
    ISO_APP_ACTIVITY("com.saucelabs.mydemoapp.rn.MainActivity"),
    IOS_APP_PACKAGE("com.saucelabs.mydemoapp.rn"),
    EMULATOR_AVD_NAME("Pixel_8_Pro"),
    IOS_PLATFORM("iOS"),
    IOS_DEVICENAME("iPhone 16 Pro"),
    EMULATOR_UDID("emulator-5554"),
    EMULATOR_STARTING("Emulator is Launching!"),
    EMULATOR_STARED("Emulator has been launched!"),
    EMULATOR_STARTING_FAIL("Not able to connect Emulator!!"),
    EMULATOR_STOPING("Stopping emulator!!"),
    EMULATOR_STOPED("Emulator has been stopped successfully!!"),
    EMULATOR_STOPING_FAIL("Not able to stop Emulator!!"),

    /**
     * @iOS
     * iOS Mobile Device specifications
     */
    ISO_APP_PATH("/Users/siteshkumarvishwakarma/ME/Stuffs/MobileApps/UIKitCatalog.app"),
    ISO_IPA_PATH("/Users/siteshkumarvishwakarma/Documents/Stuffs/MobileApps/iOSDemoApp.ipa"),
    IOS_PLATFORM_VERSION("18.5"),
    IOS_AUTOMATION_NAME("XCUITest"),
    IOS_UDID_NAME("45564E01-5769-440E-896B-0DF7586F08A9"),
    IOS_BUNDLE_ID("com.saucelabs.mydemoapp.rn"),

    /**
     * @Files
     * Filepaths
     */
    WORD_FILE_PATH("/Users/siteshkumarvishwakarma/Downloads/Raju_Kumar_Vishwakarma.docx"),


    /**
     * @Test
     * Tests Data
     */
    USERNAME("username"),
    PASSWORD("password"),
    EMAIL("email"),

    /**
     * @Sleep
     * Sleep times
     */
    SCREEN_TIMEOUT("screenTimeout"),
    ELEMENT_TIMEOUT("elementTimeout"),
    SLEEP_IN_MSECS2("threadSleepTime2"),
    SLEEP_IN_MSECS5("threadSleepTime5"),


    /**
     * @Logger
     * Logger Messages
     */
    LAUNCHING_MOBILE_APPLICATION("launching_Mobile_Application"),
    MOBILE_APP_LAUNCHED_SUCCESSFULLY("Mobile Application is launched Successfully"),
    HANDLING_CONTEXTS("handling_Contexts"),
    CONTEXT_HANDLED_SUCCESSFULLY("context_is_handled_successfully"),
    APP_KILL("killing_the_Mobile_Application"),
    SUCCESSFUL_KILL_APP("mobile_Application_is_killed_successfully"),
    SUCCESSFUL_SCREENSHOT("screenshot_is_taken_successfully"),
    LOADING_PROP_FILE("Loading_Property_File"),
    PROP_FILE_NOT_LOADED("Property_File_is_not_Loaded"),
    ALERT_NOT_HANDLED("Alert_not_handled"),
    LOADING_RESOURCES("loading_resources"),
    LOADING_SHEET("loading_sheet"),
    LOADING_JSON_DATA("loading_json_data"),
    LOADING_EXCEL_DATA("Loading_Excel_Data"),
    STARTING_APPIUM_SERVER("starting_appium_server"),
    APPIUM_SERVER_STARTED("appium_server_is_started_successfully"),
    STOPPING_APPIUM_SERVER("stopping_appium_server"),
    APPIUM_SERVER_STOPPED("appium_server_is_stopped_successfully"),

    /**
     * @Error&Info
     * Error and Exception Messages
     */
    CANNOT_CREATE_COPY_OBJECT("cannot_create_the_Another_copy_of_this_Object"),
    THIS_STEP_FAILED("this_Step_is_failed"),
    PROVIDE_VALID_OS_NAME("provide_Valid_OS_Name"),
    OS_NAME_MATCHED("os_name_Matched"),
    DRIVER_IS_GETTING_NULL("driver_is_getting_null"),
    NO_DRIVER_FOUND("no_driver_found"),
    DRIVER_NUL("driver_is_null"),
    ALERT_EXCEPTION("Alert_handle_Exception"),
    WRONG_PASSWORD("Entered_Password_is_wrong"),
    ERROR_TITLE_PRINT("Error_Title_print"),
    ERROR_MESSAGE("Error_Message"),
    ERROR_TITLE_VALIDATE("Error_Title_Validate"),
    SERVER_NOT_STARTED("Server_not_started"),
    SERVER_CANNOT_STARTED("Server_cannot_be_started"),
    SERVER_NOT_STOPPED("Server_not_stopped"),
    SERVER_CANNOT_STOPPED("Server_cannot_be_stopped"),
    APPIUM_SERVER_NOT_STARTED("Appium_Server_Not_Started_ABORT"),
    FILE_NOT_LOADED("File_not_Loaded"),
    FILE_LOADED("File_loaded"),
    NOT_PROPER_CAPABILITIES("not_proper_caps"),
    START_SCREEN_RECORDING("start_recording"),
    STOP_SCREEN_RECORDING("stop_recording"),
    STOP_LOGGING("stop_logging"),

    /**
     * @Contexts
     * Context Views
     */
    WEB_VIEW_COM_SE_INDUS("web_view_com_se_indus"),
    WEB_VIEW_COM_SE("web_view_com_se"),
    WEB_VIEW("web_view"),
    CONTEXT_NAME("contextName"),

    /**
     * @Screenshot_Recording
     * Type of the screenshot
     */
    SCREENSHOT_IMAGE_TYPE("image_png"),
    SCREEN_RECORDING_PATH("screen_recording_path"),
    SCREEN_RECORDING_FILE_NAME("screen_recording_file_name"),
    IS_NOT_A_DIRECTORY("is_not_a_directory"),
    yyyy_MM_dd_HH_mm_ss("yyyy_MM_dd_HH_mm_ss"),


    /**
     * @Browsername specific data
     */
    BROWSER_NAME_CHROME("browserNameChrome"),
    BROWSER_NAME_FIREFOX("browserNameFirefox"),
    BROWSER_NAME_EDGE("browserNameEdge"),

    /**
     * @Browsername Logger Messages
     */
    LAUNCHING_BROWSER("launching_the_browser"),
    BROWSER_LAUNCHED("browser_is_launched_Successfully"),
    CLOSING_BROWSER("closing_the_browser"),
    BROWSER_CLOSED("browser_is_closed_successfully"),
    BROWSER_NAME_NOT_MATCHED("browser_name_not_matched"),
    BASE_URL("base_url");

    private final String _value;

    PropertyConfig(String value) {
        _value = value;
    }

    @Override
    public String toString() {
        return _value;
    }
}
