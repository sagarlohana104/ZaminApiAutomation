package ApiAutomation.TestCases.Auth.AuthStepDefinitions;

import ApiAutomation.Neuconnect.POM.Auth.Actions.ActionGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Actions.Actions;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Asserts.AssertUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddActionsStepDefinitions {
    public static String loginResponse,jwtToken,name,tag,addActionResponse;
    public static String actionId,deleteActionResponse;
    private Scenario scenario;

    // Inject Scenario object
    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("the super admin is logged in with following credentials to add an action")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToAddanAction() {
            loginResponse = Login.login(Credentials.AuthEmail, Credentials.AuthPass);
            this.jwtToken= AuthGenerics.getJwtToken(loginResponse);

    }

    @When("the admin finds a unique action and tag")
    public void theAdminFindsAUniqueActionAndTag() {
            this.name = NameUtils.getUniqueName("Action");
            this.tag = NameUtils.getUniqueName("Tag");
    }


    @Then("admin calls the add action endpoint")
    public void adminCallsTheAddActionEndpoint() {
            this.addActionResponse = Actions.addActions(name,tag,jwtToken);
            PrintUtil.PrintSuccessLog("ADD ACTION RESPONSE : "+ addActionResponse);
    }

    @And("verifies to receive a status code of {int} after an action is added")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterAnActionIsAdded(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(addActionResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after an action is added")
    public void verifiesToReceiveAMessageAfterAnActionIsAdded(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(addActionResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("verifies the list contains the action name")
    public void verifiesTheListContainsTheActionName() {
        PrintUtil.PrintSuccessLog("GET ALL ACTION : "+Actions.getAllAction(jwtToken));
        assertTrue(RoleGenerics.findRoleFromRolesList(Actions.getAllAction(jwtToken),name,"name"));
    }

    @And("fetches the action id")
    public void fetchesTheActionId() {
        this.actionId= ActionGenerics.getActionDetailFromActionName(Actions.getAllAction(jwtToken),name,"id");
        PrintUtil.PrintSuccessLog("ACTION ID : "+ actionId);
    }

    @And("deletes the action")
    public void deletesTheAction() {
        deleteActionResponse = Actions.deleteAction(actionId,jwtToken);
    }

    @When("the admin has action id {string} and {string}")
    public void theAdminHasActionIdAnd(String actionId, String tag) {
        this.actionId=actionId;
        this.tag=tag;
    }



    @When("the admin finds duplicate action id with tag {string}")
    public void theAdminFindsDuplicateActionIdWithTag(String tag) {
        this.tag=tag;
        this.name=ActionGenerics.getLastActionDetails(Actions.getAllAction(jwtToken),"name","2");
    }

    @When("the admin has an unauthorized user")
    public void theAdminHasAnUnauthorizedUser() {
        jwtToken="";
    }

    @When("the admin has whitespaces as payload")
    public void theAdminHasWhitespacesAsPayload() {
        name="";
        tag="";
    }


    @And("verifies the exception message {string} for the exception {string} for adding action heh")
    public void verifiesTheExceptionMessageForTheExceptionForAddingAction(String arg0, String arg1) {
        AssertUtils.verifiesToReceiveException(arg0,arg1,addActionResponse,scenario.getName());
    }


}
