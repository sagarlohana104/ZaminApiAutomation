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
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;

public class DeleteRoleStepDefinitions {
    private String loginResponse;
    private String jwtToken;
    private String roleName ;
    private String addRoleResponse ;
    private String deleteRoleResponse;
//    private String roleId;
    private String listRoleResponse;
    private String createRoleResponse;
    private String ListRolesResponse;
    private String RoleId;

    @Given("the super admin is logged in with following credentials to delete a role")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsAndToAddARole()
    {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }

    @When("the super admin finds a unique role name")
    public void theSuperAdminFindsAUniqueRoleName() {
        this.roleName= NameUtils.getUniqueName("Role");
//        createRoleResponse = Role.createRole(roleName,jwtToken);
//        System.out.println("createRoleResponse: "+createRoleResponse);

//        ListRolesResponse = Role.listRoles(jwtToken);
//        System.out.println("ListRolesResponse: "+ListRolesResponse);
//
//        this.RoleId = SearchInResponse.getRoleIdByRoleName(ListRolesResponse,roleName);
//        System.out.println("RoleId: "+RoleId);
    }

    @And("the super admin calls the add role endpoint")
    public void theSuperAdminCallsTheAddRoleEndpoint() {
//        this.deleteRoleResponse= Role.deleteRole(RoleId,jwtToken);
//        System.out.println("deleteRoleResponse: "+deleteRoleResponse);
        createRoleResponse = Role.createRole(roleName,jwtToken,"","");
        System.out.println("createRoleResponse: "+createRoleResponse);
    }

    @And("then extracts the roleId")
    public void thenExtractsTheRoleId() {
        ListRolesResponse = Role.listRoles(jwtToken);
        System.out.println("ListRolesResponse: "+ListRolesResponse);

        this.RoleId = SearchInResponse.getRoleIdByRoleName(ListRolesResponse,roleName);
        System.out.println("RoleId: "+RoleId);
    }

    @Then("the admin calls the delete role endpoint")
    public void theAdminCallsTheDeleteRoleEndpoint() {
        this.deleteRoleResponse= Role.deleteRole(RoleId,jwtToken);
        System.out.println("deleteRoleResponse: "+deleteRoleResponse);
    }


    @And("verifies its no longer present in list all roles list")
    public void verifiesItsNoLongerPresentInListAllRolesList() {
        listRoleResponse = Role.listRoles(jwtToken);

        // Check if the RoleId is present in the response
        boolean isRoleIdPresent = SearchInResponse.isRoleIdPresentInResponse(
                listRoleResponse, "data.id", RoleId
        );

        // Assert that the RoleId is no longer present
        Assert.assertFalse("RoleId should not be present in the response", isRoleIdPresent);

        System.out.println("Verified that RoleID is no longer present in the response!");


    }

    @And("verifies to receive a status code of {int} after role deletion")
    public void verifiesToReceiveAStatusCodeOfStatusCode(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(deleteRoleResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }


    @And("verifies to receive a message of {string} after role deletion")
    public void verifiesToReceiveAMessageOfAfterRoleDeletion(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(deleteRoleResponse);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }


    @When("the super admin has a role id {string}")
    public void theSuperAdminHasARoleId(String roleId) {
        this.RoleId = roleId;
    }



    @And("the admin deletes a roleId which is already deleted")
    public void theAdminDeletesARoleIdWhichIsAlreadyDeleted() {
        this.deleteRoleResponse=Role.deleteRole(RoleId,jwtToken);
    }


    @And("verifies the exception message {string} for the exception {string} after deletion")
    public void verifiesTheExceptionMessageForTheExceptionAfterDeletion(String ExpectedExceptionMessage,String Exception) {
        String actualExceptionMessage = ApiResponseUtils.getApiResponseException(deleteRoleResponse,Exception);
        assertEquals(ExpectedExceptionMessage, actualExceptionMessage, "Expected Exception: " + ExpectedExceptionMessage + ", but got: " + actualExceptionMessage);

    }
}
