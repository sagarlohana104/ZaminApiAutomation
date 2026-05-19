package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/csp.html"
        },
        features = "src/test/resources/Features/NeuconnectFeatures",
        glue = "ApiAutomation.TestCases.Neuconnect.NeuconnectSteps",
        dryRun = false,
        tags = "@CreateInventoryTransferRequest"
)
public class NeuconnectRunner
{

}
