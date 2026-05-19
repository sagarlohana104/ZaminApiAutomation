package ApiAutomation.TestCases.Auth.AuthStepDefinitions;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.SearchInResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRoleStepDefinition {

    private String loginResponse ;
    private String jwtToken;
    private String roleName ;
    private String addRoleResponse ;
    private String listAllRolesResponse ;
    private boolean isTermPresent;
    private String roleId;

    @Given("the super admin is logged in with following credentials to Add a role")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToAddARole()
    {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }


    @When("the admin finds a unique role name to add")
    public void theAdminFindsAUniqueRoleNameToAdd() {
        this.roleName= NameUtils.getUniqueName("Role");
    }

    @Then("the admin calls the add role endpoint")
    public void theAdminCallsTheAddRoleEndpoint() {
        this.addRoleResponse= Role.createRole(roleName, jwtToken,"","");
    }

    @And("verifies to receive a status code of {int} after add role")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterAddRole(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(addRoleResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after add role")
    public void verifiesToReceiveAMessageAfterAddRole(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(addRoleResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("verifies the list contains the role name")
    public void verifiesTheListContainsTheRoleName() {
        listAllRolesResponse = Role.listRoles(jwtToken);
        PrintUtil.PrintSuccessLog("List All Role Response : "+ listAllRolesResponse);
        roleId = SearchInResponse.getRoleIdByRoleName(listAllRolesResponse,roleName);
        Assertions.assertNotNull(roleId, "Test failed: The roleId for the role name '" + roleName + "' was not found in the response.");


    }

    @When("the admin has a role name to add {string}")
    public void theAdminHasARoleNameToAdd(String roleName) {
        this.roleName=roleName;
    }


    @And("verifies the exception message {string} for the exception {string}")
    public void verifiesTheExceptionMessageForTheException(String expectedRoleNameExceptionMessage, String roleNameException) {
        String actualExceptionMessage = ApiResponseUtils.getApiResponseException(addRoleResponse,roleNameException);
        assertEquals(expectedRoleNameExceptionMessage, actualExceptionMessage, "Expected Exception: " + expectedRoleNameExceptionMessage + ", but got: " + actualExceptionMessage);
    }

    @When("the admin finds a duplicate role name to add")
    public void theAdminFindsADuplicateRoleNameToAdd() {
        this.listAllRolesResponse=Role.listRoles(jwtToken);
        this.roleName= RoleGenerics.getLastRoleDetails(listAllRolesResponse , "name","0");
        PrintUtil.PrintSuccessLog("ROLE NAME : "+roleName);
    }

    @And("retrieves its role id")
    public void retrievesItsRoleId() {
        listAllRolesResponse = Role.listRoles(jwtToken);
        this.roleId = SearchInResponse.getRoleIdByRoleName(listAllRolesResponse,roleName);
        PrintUtil.PrintSuccessLog("ROLE ID : "+ roleId);
    }
}
