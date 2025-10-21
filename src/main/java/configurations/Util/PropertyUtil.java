package configurations.Util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyUtil {

    /**
     * @keyword Using volatile keyword get the proper and refreshed value for all threads.
     */
    private volatile static PropertyUtil propertyUtil;


    /**
     * Restricted user to create a copy of this constructor
     * @Throws IOException
     */
    private PropertyUtil() throws IOException {
        if (propertyUtil != null) {
            throw new RuntimeException(PropertyUtil.propertyInstance()
                    .getPropertyData().getProperty(PropertyConfig.CANNOT_CREATE_COPY_OBJECT.toString())
                    + propertyUtil.getClass().getSimpleName());
        }
    }


    /**
     * @return driverManager
     * @implNote Double check code for driver is initiated or not.
     */
    public static PropertyUtil propertyInstance() throws IOException {

        if (propertyUtil == null) {
            synchronized (PropertyUtil.class) {
                if (propertyUtil == null) {
                    propertyUtil = new PropertyUtil();
                }
            }
        }
        return propertyUtil;
    }


    /**
     * Creating instance of Properties
     */
    public static final Properties properties = new Properties();


    /**
     * @method getResources
     * @return properties
     * @Throws IOException
     * @implNote Getting called the method 'getResources' and loading the resource
     */
    public Properties getPropertyData() throws IOException {
        if(properties.isEmpty()) {
            GenericUtil.genericUtilInstance().getLog().info("FileConstants.LOADING_PROPERTY_FILE_MESSAGE");
            try (InputStream inputStream = GenericUtil.genericUtilInstance().getResources("/Users/siteshkumarvishwakarma/IdeaProjects/MobileDeskUI/src/test/resources/config.properties")) {
                properties.load(inputStream);
            } catch (Exception exception) {
                throw new RuntimeException("FileConstants.PROPERTY_FILE_NOT_LOADED_MESSAGE");
            }
            if(!properties.isEmpty()) {
                GenericUtil.genericUtilInstance().getLog().info("FileConstants.PROPERTY_FILE_LOADED");
            }
        }
        return properties;
    }
}
