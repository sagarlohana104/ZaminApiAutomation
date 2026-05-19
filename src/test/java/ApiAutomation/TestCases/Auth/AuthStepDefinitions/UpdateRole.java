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

public class UpdateRole {
    private String loginResponse;
    private String jwtToken;
    private String listAllRoleResponse ;
    private String RoleId ;
    private String RoleName ;
    private String updateRoleResponse ;
    private String addRoleResponse ;
    private String deleteRoleResponse ;
    private String CreateRoleResponse;

    @Given("the super admin is logged in with following credentials to update a role")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsAndToAddARole()
    {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }

    @When("the admin gets the roles list")
    public void theAdminGetsTheRolesList() {
//        this.listAllRoleResponse= Role.listRoles(jwtToken);
    }

    @Then("the admin retrieves the roleId")
    public void theAdminRetrievesTheRoleId() {
        RoleName = NameUtils.getUniqueName("Role1");
        CreateRoleResponse = Role.createRole(RoleName,jwtToken,"","");
        this.listAllRoleResponse= Role.listRoles(jwtToken);
        this.RoleId= SearchInResponse.getRoleIdByRoleName(listAllRoleResponse,RoleName);
    }

    @And("updates the role with a unique roleName")
    public void updatesTheRoleWithAUniqueRoleName() {
        this.updateRoleResponse= Role.updateRole(RoleId, NameUtils.getUniqueName("NewRole"),jwtToken);
        PrintUtil.PrintSuccessLog("UPDATED ROLE RESPONSE : "+updateRoleResponse);
    }

    @And("verifies to receive a status code of {int} for role update")
    public void verifiesToReceiveAStatusCodeOfStatusCodeForRoleUpdate(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(updateRoleResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} for role update")
    public void verifiesToReceiveAMessageForRoleUpdate(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(updateRoleResponse);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("verifies the roles list contains the updated role name")
    public void verifiesTheRolesListContainsTheUpdatedRoleName() {
        this.listAllRoleResponse=Role.listRoles(jwtToken);
//        assertTrue(RoleGenerics.findRoleFromRolesList(Role.listRoles(jwtToken),roleName));
        RoleGenerics.findRoleFromRolesList(Role.listRoles(jwtToken),RoleName,"name");

    }

    @When("the admin has a roleId {string} and {string} to update")
    public void theAdminHasARoleIdAndToUpdate(String RoleId, String RoleName) {
        this.RoleId=RoleId;
        this.RoleName=RoleName;
    }

    @Then("updates the role")
    public void updatesTheRole() {
        this.updateRoleResponse=Role.updateRole(RoleId,RoleName,jwtToken);
    }
//
    @And("verifies the receive a role update exception message {string} for the exception {string}")
    public void verifiesTheReceiveARoleUpdateExceptionMessageForTheException(String expectedExceptionMessage, String exception) {
        String actualExceptionMessage = ApiResponseUtils.getApiResponseException(updateRoleResponse,exception);
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage, "Expected Exception: " +expectedExceptionMessage + ", but got: " + actualExceptionMessage);
    }

    @When("the admin has a unique role name")
    public void theAdminHasAUniqueRoleName() {
        this.RoleName=NameUtils.getUniqueName("Role");
    }

    @And("the admin calls the add role endpoint for updation")
    public void theAdminCallsTheAddRoleEndpointForUpdation() {
        this.addRoleResponse=Role.createRole(RoleName,jwtToken,"","");
    }

    @And("the admin deletes that role")
    public void theAdminDeletesThatRole() {
        this.deleteRoleResponse=Role.deleteRole(RoleId,jwtToken);
    }

    @When("the admin has super admins role name")
    public void theAdminHasSuperAdminsRoleName() {
        this.RoleName="SuperAdmin";
    }

    @And("enters roleName {string}")
    public void entersRoleName(String RoleName) {
        this.RoleName = RoleName;

    }



}
