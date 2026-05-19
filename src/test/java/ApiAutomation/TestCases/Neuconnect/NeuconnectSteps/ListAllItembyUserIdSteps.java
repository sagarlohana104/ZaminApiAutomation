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

import javax.sql.rowset.spi.SyncResolver;

public class ListAllItembyUserIdSteps {
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
    private Scenario scenario;

    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }

    public String getListAllItemUserByIdresp() {
        return ListAllItemUserByIdresp;
    }

    public void setListAllItemUserByIdresp(String listAllItemUserByIdresp) {
        ListAllItemUserByIdresp = listAllItemUserByIdresp;
    }

    private String ListAllItemUserByIdresp;



    @Given("A user has logged in access with ListAllItembyuserid")
    public void aUserHasLoggedInAccessWithListAllItembyuserid() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("Extract user id from the list to test ListAllItembyuserid")
    public void extractUserIdFromTheListToTestListAllItembyuserid() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setUserId(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListUsers(getJwtToken()),"data.users","userId","0"
        ));
        PrintUtil.PrintErrorLog("User id extract from user list " + getUserId());

    }

    @And("ListAllItembyuserid  api called")
    public void listallitembyuseridApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setListAllItemUserByIdresp(NeuconnectFunction.listAllItemCodebyUserId(getJwtToken(),getUserId()));
        PrintUtil.PrintSuccessLog(getListAllItemUserByIdresp());


    }

    @And("isApiHandled is verified as {string} to test ListAllItembyuserid")
    public void isapihandledIsVerifiedAsToTestListAllItembyuserid(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListAllItemUserByIdresp(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test ListAllItembyuserid")
    public void isrequestsuccessIsVerifiedAsToTestListAllItembyuserid(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListAllItemUserByIdresp(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list ListAllItembyuserid")
    public void statuscodeIsVerifiedAsStatusCodeToTestListListAllItembyuserid(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListAllItemUserByIdresp(),scenario.getName());

    }

    @And("message is verified {string} to test list ListAllItembyuserid")
    public void messageIsVerifiedToTestListListAllItembyuserid(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListAllItemUserByIdresp(),scenario.getName());
    }
}
