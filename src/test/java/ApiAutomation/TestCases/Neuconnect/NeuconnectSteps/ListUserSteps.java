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

import java.security.Principal;

public class ListUserSteps {
    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    private String JwtToken;

    public String getListUserResponse() {
        return ListUserResponse;
    }

    public void setListUserResponse(String listUserResponse) {
        ListUserResponse = listUserResponse;
    }

    private String ListUserResponse;
    private Scenario scenario;

    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }

    @Given("A user has logged in access with list user")
    public void Auserhasloggedinaccesswithlistuser(){
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());

    }

    @When("List user api called")
    public void listUserApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setListUserResponse(NeuconnectFunction.ListUsers(getJwtToken()));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }


    @And("isApiHandled is verified as {string} to test ListUser")
    public void isapihandledIsVerifiedAsToTestListUser(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListUserResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test listUser")
    public void isrequestsuccessIsVerifiedAsToTestListUser(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListUserResponse(),scenario.getName());

    }

    @And("statusCode is verified as {int} to test list user")
    public void statuscodeIsVerifiedAsStatusCodeToTestListUser(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListUserResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list user")
    public void messageIsVerifiedToTestListUser(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListUserResponse(),scenario.getName());
    }
}
