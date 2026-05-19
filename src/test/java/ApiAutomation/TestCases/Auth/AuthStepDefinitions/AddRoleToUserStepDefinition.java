package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.User;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.UserGenerics;
import ApiAutomation.Neuconnect.Utils.*;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRoleToUserStepDefinition {
    private String loginResponse;
    private String jwtToken;
    private String userEmail ;
    private String userName ;
    private String userType ;
    private String roleName ;
    private String roleName2;
    private String roleId2;
    private String addRoleResponse ;
    private String addUserResponse ;
    private String listUsers ;
    private String listRoles ;
    private String roleId ;
    private String userId ;
    private String assignRoleResponse ;
    private String userLoginResponse ;
    private String listAllRolesResponse;


    @Given("the super admin is logged in with following credentials to add role to user")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToAddARole()
    {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
//        PrintUtil.PrintSuccessLog("LOGIN RESPONSE : "+ loginResponse);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
        PrintUtil.PrintSuccessLog("JWT TOKEN : "+ jwtToken);
    }

    @When("the admin gets unique user and name details")
    public void theAdminGetsUniqueUserAndNameDetails() {
        this.userEmail= NameUtils.getUniqueName("Email") + "@gmail.com";
        this.userName= NameUtils.getUniqueName("User");
        this.userType= Constants.AGEEK;
        this.roleName=NameUtils.getUniqueName("Role");
    }

    @And("admin creates a unique user {string}")
    public void adminCreatesAUniqueUserWithTheFollowingRoleIdAnd( String password) {
        this.addUserResponse= User.addUser(userEmail, userName, password, password, "2024-09-25T06:38:23.735Z", Arrays.asList(roleId), userType, jwtToken);
        PrintUtil.PrintSuccessLog("ADD USER RESPONSE : "+ addUserResponse);
    }

    @And("retrieves its user id")
    public void retrievesItsUserId() {
        this.listUsers=User.ListUsers(jwtToken);
        this.userId= UserGenerics.getUserDetailFromUserName(listUsers,userName,"userId");
        PrintUtil.PrintSuccessLog("USER ID : "+ userId);
    }

    @And("admin creates a unique role")
    public void adminCreatesAUniqueRole() {
        this.addRoleResponse= Role.createRole(roleName,jwtToken,"","");
    }

    @Then("admin assigns a role to that user")
    public void adminAssignsARoleToThatUser() {
        this.assignRoleResponse=Role.addRoleToUser(userId,roleId,jwtToken);
    }

    @And("verifies to receive a status code of {int} after role assignment")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterRoleAssignment(int expectedStatusCode ) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(assignRoleResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after role assignment")
    public void verifiesToReceiveAMessageAfterRoleAssignment(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(assignRoleResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("deletes the unique user and role in the end")
    public void deletesTheUniqueUserAndRoleInTheEnd() {
        PrintUtil.PrintSuccessLog("DELETE USER : " + User.deleteUser(userId,jwtToken));
//        PrintUtil.PrintSuccessLog("DELETE ROLE : " + Role.deleteRole(roleId,jwtToken));
    }

    @And("admin creates a second unique role")
    public void adminCreatesASecondUniqueRole() {
        this.roleName2=NameUtils.getUniqueName("Role2");
        this.addRoleResponse=Role.createRole(roleName2,jwtToken,"","");
    }

    @And("retrieves role id for the second role")
    public void retrievesRoleIdForTheSecondRole() {
        listAllRolesResponse = Role.listRoles(jwtToken);
        this.roleId2 = SearchInResponse.getRoleIdByRoleName(listAllRolesResponse,roleName2);
    }

    @And("admin assigns a second role to that user")
    public void adminAssignsASecondRoleToThatUser() {
        this.assignRoleResponse=Role.addRoleToUser(userId,roleId2,jwtToken);
    }

    @When("the admin gets a valid user id")
    public void theAdminGetsAValidUserId() {
        this.listUsers=User.ListUsers(jwtToken);
        this.userId=UserGenerics.getLastUserDetails(listUsers,"userId","4");
    }

    @And("the admin has a unique role name in add role to to user")
    public void theAdminHasAUniqueRoleNameInAddRoleToToUser() {
        this.roleName=NameUtils.getUniqueName("Role");
    }

    @When("the admin finds a user id {string}")
    public void theAdminFindsAUserId(String userId) {
        this.userId=userId;
    }

    @And("the admin finds a roleId {string}")
    public void theAdminFindsARoleId(String roleId) {
        this.roleId=roleId;
    }

    @When("the admin gets a super admin user")
    public void theAdminGetsASuperAdminUser() {
        this.userId=UserGenerics.getUserDetailFromUserName(User.ListUsers(jwtToken),Constants.UserName,"userId");
    }

    @And("deletes the role")
    public void deletesTheRole() {
        Role.deleteRole(roleId,jwtToken);
    }

    @And("verifies that role is not assigned to the user by logging in with {string}")
    public void verifiesThatRoleIsNotAssignedToTheUserByLoggingInWith(String userPass) {
        this.userLoginResponse= Login.login(this.userEmail,userPass);
        assertFalse(AuthGenerics.findDetailFromAuthResponse(userLoginResponse,roleName,"roleAndActions","roleName"));


    }

    @And("admin retrieves its role id")
    public void adminRetrievesItsRoleId() {
        listAllRolesResponse = Role.listRoles(jwtToken);
        this.roleId = SearchInResponse.getRoleIdByRoleName(listAllRolesResponse,roleName);
    }

    @And("deletes the second role")
    public void deletesTheSecondRole() {
        Role.deleteRole(roleId2,jwtToken);
    }

    @And("the admin has a valid role")
    public void theAdminHasAValidRole() {
        roleId= RoleGenerics.getLastRoleDetails(Role.listRoles(jwtToken),"id","2");
    }

    @And("admin creates a unique user with the following roleId {string} and {string}")
    public void adminCreatesAUniqueUserWithTheFollowingRoleIdAnd(String roleId, String password) {
        this.addUserResponse= User.addUser(userEmail, userName, password, password, "2024-09-25T06:38:23.735Z", Arrays.asList(roleId), userType, jwtToken);
        PrintUtil.PrintSuccessLog("ADD USER RESPONSE : "+ addUserResponse);
    }
}
