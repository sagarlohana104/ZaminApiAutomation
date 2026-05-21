package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
import ApiAutomation.Neuconnect.Utils.Asserts.AssertUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.security.SecureRandom;

public class ListAllProductionOrdersSteps {
    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    private String  JwtToken;
    private Scenario scenario;

    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }

    public String getListAllProductionOrdersResponse() {
        return ListAllProductionOrdersResponse;
    }

    public void setListAllProductionOrdersResponse(String listAllProductionOrdersResponse) {
        ListAllProductionOrdersResponse = listAllProductionOrdersResponse;
    }

    private String ListAllProductionOrdersResponse;


    @Given("A user has logged in access with list all production order")
    public void aUserHasLoggedInAccessWithListAllProductionOrder() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("List  production order user id  api called")
    public void listProductionOrderUserIdApiCalled() {
        setListAllProductionOrdersResponse(NeuconnectFunction.ListAllProductionOrders(getJwtToken()));
        PrintUtil.PrintSuccessLog(getListAllProductionOrdersResponse());

    }

    @And("isApiHandled is verified as {string} to test List production order")
    public void isapihandledIsVerifiedAsToTestListProductionOrder(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListAllProductionOrdersResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list production order")
    public void isrequestsuccessIsVerifiedAsToTestListProductionOrder(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListAllProductionOrdersResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list production order")
    public void statuscodeIsVerifiedAsStatusCodeToTestListProductionOrder(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListAllProductionOrdersResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list production order")
    public void messageIsVerifiedToTestListProductionOrder(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListAllProductionOrdersResponse(),scenario.getName());
    }
}
