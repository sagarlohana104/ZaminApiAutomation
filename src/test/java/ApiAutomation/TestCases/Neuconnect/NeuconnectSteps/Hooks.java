package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.Utils.env.envConfig;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks
{
    @Before
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation();
        String baseURL = System.getenv("BASE_URL") != null
                ? System.getenv("BASE_URL")
                : envConfig.getEnv("BASE_URL");

        int port = System.getenv("PORT") != null
                ? Integer.parseInt(System.getenv("PORT"))
                : envConfig.getEnvInteger("PORT");

        // Set RestAssured configurations
        RestAssured.baseURI = baseURL;
        RestAssured.port = port;
}
    }