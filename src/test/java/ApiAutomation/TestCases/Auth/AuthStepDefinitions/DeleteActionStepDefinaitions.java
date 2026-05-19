package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Actions.ActionGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Actions.Actions;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteActionStepDefinaitions {

    private String loginResponse;
    private String jwtToken ;
    private String actionName ;
    private String tag ;
    private String actionId ;
    private String deleteAction;

    @Given("the super admin is logged in with following credentials to delete an action")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsAndToDeleteAnAction() {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }

    @When("the admin has unique action details")
    public void theAdminHasUniqueActionDetails() {
        this.actionName= NameUtils.getUniqueName("Action");
        this.tag = NameUtils.getUniqueName("Tag");
    }


    @And("a new action is made")
    public void aNewActionIsMade() {
        Actions.addActions(actionName,tag,jwtToken);
    }

    @And("action id is fetched")
    public void actionIdIsFetched() {
        this.actionId= ActionGenerics.getActionDetailFromActionName(Actions.getAllAction(jwtToken),actionName,"id");
        System.out.println("ActionId: "+actionId);
    }

    @Then("action is deleted")
    public void actionIsDeleted() {
        this.deleteAction=Actions.deleteAction(actionId,jwtToken);
    }

    @And("verifies to receive a status code of {int} after action is deleted")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterActionIsDeleted(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(deleteAction);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after action is deleted")
    public void verifiesToReceiveAMessageAfterActionIsDeleted(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(deleteAction);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("verifies the list does not contain the action")
    public void verifiesTheListDoesNotContainTheAction() {
        assertFalse(ActionGenerics.findActionFromActionsList(Actions.getAllAction(jwtToken),actionName,"name"));
    }
}

