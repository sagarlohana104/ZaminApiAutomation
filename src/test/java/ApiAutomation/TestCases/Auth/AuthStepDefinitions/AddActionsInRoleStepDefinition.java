package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import ApiAutomation.Neuconnect.Utils.SearchInResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddActionsInRoleStepDefinition {
    private static String loginResponse;
    private static String jwtToken;
    private static String roleName,ListRoleResponse,roleId,addActionsInRoleResponse;
    private static List<String> actions;






    @When("Create and fetch roleId")
    public void createAndFetchRoleId() {
        roleName = NameUtils.getUniqueName("Role");
        Role.createRole(roleName,jwtToken,"","");

        ListRoleResponse = Role.listRoles(jwtToken);
        System.out.println("ListRoleResponse: "+ListRoleResponse);

        roleId = SearchInResponse.getRoleIdByRoleName(ListRoleResponse, roleName);
        System.out.println("roleId1: "+roleId);
    }


    @Then("verifies to receive a status code of {int} when AddActionsInRole is hit")
    public void verifiesToReceiveAStatusCodeOfStatusCodeWhenAddActionsInRoleIsHit(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(addActionsInRoleResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} when AddActionsInRole is hit")
    public void verifiesToReceiveAMessageWhenAddActionsInRoleIsHit(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(addActionsInRoleResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);

    }

    @Given("the super admin is logged in with following credentials to add actions in a role")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToAddActionsInARole() {
        loginResponse = Login.login(Credentials.AuthEmail, Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
        System.out.println("LoginResposne: "+loginResponse);
    }

    @And("admin hits the API to add multiple actions in a role")
    public void adminHitsTheAPIToAddMultipleActionsInARole() {
        List<String> actions = Arrays.asList(
                "/Auth/IAuthFeature/GetOTPHistoryLogs",
                "/Auth/IRoleManagementFeature/CreateRole"
        );
        addActionsInRoleResponse = Role.addActionsInRole(roleId,actions,jwtToken);
        System.out.println("addActionsInRole: "+addActionsInRoleResponse);
    }
}
