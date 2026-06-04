package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
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

public class ListAllCustomeruseridSteps {
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String jwtToken;
    private String userId;
    private Scenario scenario;

    public String getListallCustomerResponse() {
        return listallCustomerResponse;
    }

    public void setListallCustomerResponse(String listallCustomerResponse) {
        this.listallCustomerResponse = listallCustomerResponse;
    }

    private String listallCustomerResponse;


    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }
    @Given("A user has logged in access with ListAllCustomeruserid")
    public void aUserHasLoggedInAccessWithListAllCustomeruserid() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());

    }

    @When("Extract user id from the list to test ListAllCustomeruserid")
    public void extractUserIdFromTheListToTestListAllCustomeruserid() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setUserId(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListUsers(getJwtToken()),"data.users","userId","3"
        ));
        PrintUtil.PrintErrorLog("User id extract " + getUserId());
    }

    @And("ListAllCustomeruserid  api called")
    public void listallcustomeruseridApiCalled() {
        setListallCustomerResponse(NeuconnectFunction.ListAllCustomersOfUser(getJwtToken(),getUserId()));
        PrintUtil.PrintSuccessLog(getListallCustomerResponse());

    }

    @And("isApiHandled is verified as {string} to test ListAllCustomeruserid")
    public void isapihandledIsVerifiedAsToTestListAllCustomeruserid(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListallCustomerResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test ListAllCustomeruserid")
    public void isrequestsuccessIsVerifiedAsToTestListAllCustomeruserid(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListallCustomerResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list ListAllCustomeruserid")
    public void statuscodeIsVerifiedAsStatusCodeToTestListListAllCustomeruserid(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListallCustomerResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list ListAllCustomeruserid")
    public void messageIsVerifiedToTestListListAllCustomeruserid(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListallCustomerResponse(),scenario.getName());
    }
}
