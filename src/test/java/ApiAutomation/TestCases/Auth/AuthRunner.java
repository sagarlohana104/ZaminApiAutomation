package ApiAutomation.TestCases.Auth;



import ApiAutomation.TestCases.BaseRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/auth.html"
        },
        features = "src/test/resources/features/authFeature",
        glue = "ApiAutomationAjeek.TestCases.Auth.AuthStepDefinitions",
        dryRun = false,
        tags = "@ForgetPassword"
)
public class AuthRunner extends BaseRunner
{
}
