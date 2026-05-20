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

import java.security.SecureRandom;
import java.util.Scanner;

public class ListDirectITSteps {
    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    private String JwtToken;
    private Scenario scenario;

    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }

    public String getListDirectITResponse() {
        return ListDirectITResponse;
    }

    public void setListDirectITResponse(String listDirectITResponse) {
        ListDirectITResponse = listDirectITResponse;
    }

    private String ListDirectITResponse;
    @Given("A user has logged in access with CreateInventoryTransfer")
    public void aUserHasLoggedInAccessWithCreateInventoryTransfer() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());

    }

    @And("List All direct IT")
    public void listAllDirectIT() {
        setListDirectITResponse(NeuconnectFunction.ListAllInventoryTransfers(getJwtToken()));
        PrintUtil.PrintErrorLog("Response " + getListDirectITResponse());
    }

    @And("isApiHandled is verified as {string} to test ListInventoryTransfer")
    public void isapihandledIsVerifiedAsToTestListInventoryTransfer(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListDirectITResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list ListInventoryTransfer")
    public void isrequestsuccessIsVerifiedAsToTestListListInventoryTransfer(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListDirectITResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list ListInventoryTransfer")
    public void statuscodeIsVerifiedAsStatusCodeToTestListListInventoryTransfer(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListDirectITResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list ListInventoryTransfer")
    public void messageIsVerifiedToTestListListInventoryTransfer(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListDirectITResponse(),scenario.getName());
    }
}
