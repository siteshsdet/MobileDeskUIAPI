package Steps.API;

import configurations.Driver.DriverManager;
import configurations.Util.GenericUtil;
import manager.API.APIManager.APIManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIStepTest {

    @Test
    public void getSessionStorags_1() throws Exception {
        boolean answer = false;
        DriverManager.driverInstance().initializeDriver("web", "chrome", "https://www.amazon.com/");
        answer = APIManager.getAPIManager().getAccessToken("pid");
        answer = GenericUtil.genericUtilInstance().getSumPDFBasedOnColumnName("/Users/siteshkumarvishwakarma/Downloads/raju_poin.pdf", "Deposited Cash");
        Assert.assertTrue(answer, "Not validated");
    }
}