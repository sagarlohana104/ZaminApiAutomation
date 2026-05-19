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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddActionInRoles
{
    private static String loginResponse;
    private static String jwtToken;
    private static String getOverallResponse,ListRoleResponse,roleName1,roleName2;
    private static String actions;
    private static String AddActionInRolesResponse;
    private static List<String> roleIds;
    private static String roleId1,roleId2;


    @Given("the super admin is logged in with following credentials to add a role")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsAndToanAction() {
        loginResponse = Login.login(Credentials.AuthEmail, Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
        System.out.println("LoginResposne: "+loginResponse);
    }


    @When("admin fetch the action")
    public void adminFetchTheAction() {
        this.actions ="/Auth/IAuthFeature/GetOTPHistoryLogs";
    }

    @Then("admin fetch the roles")
    public void adminFetchTheAppUserRole() {
        roleName1 = NameUtils.getUniqueName("Role");
        roleName2 = NameUtils.getUniqueName("Role");
        Role.createRole(roleName1,jwtToken,"","");
        Role.createRole(roleName2,jwtToken,"","");

        ListRoleResponse = Role.listRoles(jwtToken);
        System.out.println("ListRoleResponse: "+ListRoleResponse);

       roleId1 = SearchInResponse.getRoleIdByRoleName(ListRoleResponse, roleName1);
       System.out.println("roleId1: "+roleId1);
        roleId2 = SearchInResponse.getRoleIdByRoleName(ListRoleResponse, roleName1);
        System.out.println("roleId2: "+roleId2);

        roleIds = List.of(roleId1,roleId2);


    }

    @And("admin hits AddActionInRoles")
    public void adminHitsAddActionInRoles() {
        AddActionInRolesResponse = Role.addActionInRoles(actions,roleIds,jwtToken);
        System.out.println("AddActionInRolesResponse: "+AddActionInRolesResponse);
    }

    @And("verifies to receive a status code of {int}")
    public void verifiesToReceiveAStatusCodeOfStatusCode(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(AddActionInRolesResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }


    @And("verifies to receive a message {string}")
    public void verifiesToReceiveAMessage(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(AddActionInRolesResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }
}
