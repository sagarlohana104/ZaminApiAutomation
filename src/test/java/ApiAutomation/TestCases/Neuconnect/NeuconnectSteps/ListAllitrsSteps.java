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

public class ListAllitrsSteps {
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;
    private Scenario scenario;

    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }

    public String getListallItrsResponse() {
        return listallItrsResponse;
    }

    public void setListallItrsResponse(String listallItrsResponse) {
        this.listallItrsResponse = listallItrsResponse;
    }

    private String listallItrsResponse;


    @Given("A user has logged in access with list all Itrs")
    public void aUserHasLoggedInAccessWithListAllItrs() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("List Itrs user id  api called")
    public void listItrsUserIdApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setListallItrsResponse(NeuconnectFunction.ListAllITRS(getJwtToken()));
        PrintUtil.PrintSuccessLog(getListallItrsResponse());

    }

    @And("isApiHandled is verified as {string} to test List Itrs")
    public void isapihandledIsVerifiedAsToTestListItrs(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListallItrsResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list Itrs")
    public void isrequestsuccessIsVerifiedAsToTestListItrs(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListallItrsResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list Itrs")
    public void statuscodeIsVerifiedAsStatusCodeToTestListItrs(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListallItrsResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list Itrs")
    public void messageIsVerifiedToTestListItrs(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListallItrsResponse(),scenario.getName());
    }
}
