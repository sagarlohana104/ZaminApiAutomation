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

public class ListAllSalesOrderSteps {
    private String JwtToken;
    private Scenario scenario;

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public String getListAllSalesOrderResponse() {
        return ListAllSalesOrderResponse;
    }

    public void setListAllSalesOrderResponse(String listAllSalesOrderResponse) {
        ListAllSalesOrderResponse = listAllSalesOrderResponse;
    }

    private String ListAllSalesOrderResponse;
    
    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }
    @Given("A user has logged in access with list listAllSalesOrder")
    public void aUserHasLoggedInAccessWithListListAllSalesOrder() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("listAllSalesOrder api called")
    public void listallsalesorderApiCalled() {
        setListAllSalesOrderResponse(NeuconnectFunction.ListAllSalesOrder(getJwtToken()));
        PrintUtil.PrintSuccessLog("List All sales order" + getListAllSalesOrderResponse());
    }

    @And("isApiHandled is verified as {string} to test listAllSalesOrder")
    public void isapihandledIsVerifiedAsToTestListAllSalesOrder(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListAllSalesOrderResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test listAllSalesOrder")
    public void isrequestsuccessIsVerifiedAsToTestListAllSalesOrder(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListAllSalesOrderResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test listAllSalesOrder")
    public void statuscodeIsVerifiedAsStatusCodeToTestListAllSalesOrder(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListAllSalesOrderResponse(),scenario.getName());
    }

    @And("message is verified {string} to test listAllSalesOrder")
    public void messageIsVerifiedToTestListAllSalesOrder(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListAllSalesOrderResponse(),scenario.getName());
    }
}
