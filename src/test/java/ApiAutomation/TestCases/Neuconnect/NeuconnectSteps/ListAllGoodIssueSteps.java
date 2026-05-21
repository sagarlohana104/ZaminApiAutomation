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

public class ListAllGoodIssueSteps {
    private String JwtToken;

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public String getListAllGoodIssueResponse() {
        return ListAllGoodIssueResponse;
    }

    public void setListAllGoodIssueResponse(String listAllGoodIssueResponse) {
        ListAllGoodIssueResponse = listAllGoodIssueResponse;
    }

    private String ListAllGoodIssueResponse;
    private Scenario scenario;

    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario= scenario;

    }

    @Given("A user has logged in access with list all goods issue")
    public void aUserHasLoggedInAccessWithListAllGoodsIssue() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("List goods issue user id  api called")
    public void listGoodsIssueUserIdApiCalled() {
        setListAllGoodIssueResponse(NeuconnectFunction.ListAllGoodIssue(getJwtToken()));
        PrintUtil.PrintSuccessLog("Response" + getListAllGoodIssueResponse());
    }

    @And("isApiHandled is verified as {string} to test List goods issue")
    public void isapihandledIsVerifiedAsToTestListGoodsIssue(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListAllGoodIssueResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test listgoodsissue")
    public void isrequestsuccessIsVerifiedAsToTestListgoodsissue(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListAllGoodIssueResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test listgoodsissue")
    public void statuscodeIsVerifiedAsStatusCodeToTestListgoodsissue(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListAllGoodIssueResponse(),scenario.getName());
    }

    @And("message is verified {string} to test listgoodsissue")
    public void messageIsVerifiedToTestListgoodsissue(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListAllGoodIssueResponse(),scenario.getName());
    }
}
