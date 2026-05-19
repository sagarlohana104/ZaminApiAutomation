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

import java.security.Principal;

public class ListWarehouseByUserIdSteps {
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;
    private Scenario scenario;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    @Before
    public void beforescenario(Scenario scenario){
        this.scenario = scenario;

    }

    public String getListwarehousebyuseridresponse() {
        return listwarehousebyuseridresponse;
    }

    public void setListwarehousebyuseridresponse(String listwarehousebyuseridresponse) {
        this.listwarehousebyuseridresponse = listwarehousebyuseridresponse;
    }

    private String listwarehousebyuseridresponse;


    @Given("A user has logged in access with list all warehouse by user id")
    public void aUserHasLoggedInAccessWithListAllWarehouseByUserId() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());


    }

    @When("List warehouse user id  api called")
    public void listWarehouseUserIdApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setListwarehousebyuseridresponse(NeuconnectFunction.ListwarehousebyuserId(getJwtToken(),""));
        PrintUtil.PrintSuccessLog(getListwarehousebyuseridresponse());

    }

    @And("isApiHandled is verified as {string} to test List warehouse by user id")
    public void isapihandledIsVerifiedAsToTestListWarehouseByUserId(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListwarehousebyuseridresponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list warehouse by user id")
    public void isrequestsuccessIsVerifiedAsToTestListWarehouseByUserId(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListwarehousebyuseridresponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list warehouse by user id")
    public void statuscodeIsVerifiedAsStatusCodeToTestListWarehouseByUserId(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListwarehousebyuseridresponse(),scenario.getName());
    }

    @And("message is verified {string} to test list warehouse by user id")
    public void messageIsVerifiedToTestListWarehouseByUserId(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListwarehousebyuseridresponse(),scenario.getName());
    }

    @And("user id extract from the user list")
    public void userIdExtractFromTheUserList() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setUserId(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListUsers(getJwtToken()),"data.users","userId","0"
        ));
        PrintUtil.PrintSuccessLog("User id extract from user list " + getUserId());

    }
}
