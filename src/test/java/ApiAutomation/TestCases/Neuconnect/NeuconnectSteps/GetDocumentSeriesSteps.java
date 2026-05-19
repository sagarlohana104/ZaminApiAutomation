package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
import ApiAutomation.Neuconnect.Utils.Asserts.AssertUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.PortUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class GetDocumentSeriesSteps {

    private String jwtToken;
    private int documentType;
    private Scenario scenario;
    private String GetDocumentResponse;

    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }

    public int getDocumentType() { return documentType; }
    public void setDocumentType(int documentType) { this.documentType = documentType; }

    public String getGetDocumentResponse() { return GetDocumentResponse; }
    public void setGetDocumentResponse(String getDocumentResponse) { GetDocumentResponse = getDocumentResponse; }

    @Before
    public void BeforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("A user has logged in access with GetDocumentSeries")
    public void aUserHasLoggedInAccessWithGetDocumentSeries() {
        setJwtToken(AuthGenerics.getJwtToken(
                Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass)
        ));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("add documentType with valid type")
    public void addDocumentTypeWithValidType() {
        setDocumentType(67);
    }

    @And("GetDocumentSeries  api called")
    public void getdocumentseriesApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setGetDocumentResponse(
                NeuconnectFunction.GetDocumentSeries(getJwtToken(), getDocumentType())
        );
        PrintUtil.PrintSuccessLog(getGetDocumentResponse());
    }

    @And("isApiHandled is verified as {string} to test GetDocumentSeries")
    public void isapihandledIsVerifiedAsToTestGetDocumentSeries(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0, getGetDocumentResponse(), scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test GetDocumentSeries")
    public void isrequestsuccessIsVerifiedAsToTestGetDocumentSeries(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0, getGetDocumentResponse(), scenario.getName());
    }

    @And("statusCode is verified as {int} to test list GetDocumentSeries")
    public void statuscodeIsVerifiedAsStatusCodeToTestListGetDocumentSeries(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0, getGetDocumentResponse(), scenario.getName());
    }

    @And("message is verified {string} to test list GetDocumentSeries")
    public void messageIsVerifiedToTestListGetDocumentSeries(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0, getGetDocumentResponse(), scenario.getName());
    }
}