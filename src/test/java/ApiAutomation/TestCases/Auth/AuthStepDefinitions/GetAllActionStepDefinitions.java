package ApiAutomation.TestCases.Auth.AuthStepDefinitions;
import ApiAutomation.Neuconnect.POM.Auth.Actions.ActionGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Actions.Actions;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetAllActionStepDefinitions {


    private String loginResponse;
    private String jwtToken;
    private String addActionResponse;
    private String actionName ;
    private String tag;
    private String getActionResponse ;

    @Given("the super admin is logged in with following credentials to get all action")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToGetAllAction()
    {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }

    @When("a new action is created")
    public void aNewActionIsCreated() {
        this.actionName= NameUtils.getUniqueName("Name");
        this.tag=NameUtils.getUniqueName("Tag");
        this.addActionResponse= Actions.addActions(actionName,tag,jwtToken);
    }

    @Then("admin calls the get action endpoint")
    public void adminCallsTheGetActionEndpoint() {
        this.getActionResponse=Actions.getAllAction(jwtToken);
    }

    @And("verifies to receive a status code of {int} after all the actions are retrieved")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterAllTheActionsAreRetrieved(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(getActionResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after all the actions are retrieved")
    public void verifiesToReceiveAMessageAfterAllTheActionsAreRetrieved(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(getActionResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("verifies the list contains the action")
    public void verifiesTheListContainsTheAction() {

        assertTrue(RoleGenerics.findRoleFromRolesList(Actions.getAllAction(jwtToken),actionName,"name"));
    }

    @And("deletes the action name")
    public void deletesTheActionName() {
        Actions.deleteAction(ActionGenerics.getActionDetailFromActionName(Actions.getAllAction(jwtToken),actionName,"id"),jwtToken);
    }

    @And("verifies the list does not contain the action name")
    public void verifiesTheListDoesNotContainTheActionName() {
        assertFalse(RoleGenerics.findRoleFromRolesList(Actions.getAllAction(jwtToken),actionName,"name"));
    }


}
