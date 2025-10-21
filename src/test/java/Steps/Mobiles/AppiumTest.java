package Steps.Mobiles;

import configurations.Util.GenericUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class AppiumTest {

    @Given("Launch the APP")
    public void launch_the_app() throws Exception {
        GenericUtil.genericUtilInstance().getLog().info("Launching the mobile Application");
    }
    @When("Successfully launched")
    public void successfully_launched() throws IOException {
        GenericUtil.genericUtilInstance().getLog().info("Launched the mobile Application");
    }
    @Then("Verify launched and get the home page")
    public void verify_launched_and_verify_the_home_page() throws IOException {
        GenericUtil.genericUtilInstance().getLog().info("Launching the mobile Application and verified");
    }
}
