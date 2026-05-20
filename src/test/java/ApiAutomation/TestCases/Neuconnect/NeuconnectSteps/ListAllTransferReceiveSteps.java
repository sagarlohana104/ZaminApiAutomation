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

import java.util.Scanner;

public class ListAllTransferReceiveSteps {
    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    private String JwtToken;

    public String getListAllTransferReceiveResponse() {
        return ListAllTransferReceiveResponse;
    }

    public void setListAllTransferReceiveResponse(String listAllTransferReceiveResponse) {
        ListAllTransferReceiveResponse = listAllTransferReceiveResponse;
    }

    private String ListAllTransferReceiveResponse;
    private Scenario scenario;

    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }
    @Given("A user has logged in access with list all ListAllTransferReceive")
    public void aUserHasLoggedInAccessWithListAllListAllTransferReceive() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("List ListAllTransferReceive user id  api called")
    public void listListAllTransferReceiveUserIdApiCalled() {
        setListAllTransferReceiveResponse(NeuconnectFunction.ListAllTransferReceive(getJwtToken()));
        PrintUtil.PrintSuccessLog(getListAllTransferReceiveResponse());

    }

    @And("isApiHandled is verified as {string} to test List ListAllTransferReceive")
    public void isapihandledIsVerifiedAsToTestListListAllTransferReceive(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListAllTransferReceiveResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list ListAllTransferReceive")
    public void isrequestsuccessIsVerifiedAsToTestListListAllTransferReceive(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListAllTransferReceiveResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list ListAllTransferReceive")
    public void statuscodeIsVerifiedAsStatusCodeToTestListListAllTransferReceive(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListAllTransferReceiveResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list ListAllTransferReceive")
    public void messageIsVerifiedToTestListListAllTransferReceive(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListAllTransferReceiveResponse(), scenario.getName());
    }
}
