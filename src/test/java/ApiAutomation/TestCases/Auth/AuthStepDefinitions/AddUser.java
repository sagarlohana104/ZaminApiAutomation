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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUser {
    private String loginResponse;
    private String jwtToken;
    private String password;
    private String email;
    private String name;
    private String type;
    private String roleId;
    private String addUserResponse;
    private String userList;
    private String confirmPassword;
    private String userId;
    private String rolesList;
    private String roleId2;
    private String ListUserResponse;
    private String fetcheduserId;
    private String roleName;
    private String createRoleResponse;
    private String userType, roleName2;

    @Given("the super admin is logged in with following credentials to add user")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToAddARole() {
        this.loginResponse = Login.login(Credentials.AuthEmail, Credentials.AuthPass);
        this.jwtToken = AuthGenerics.getJwtToken(loginResponse);
    }

    @When("the admin finds unique user details with the following password {string} and confirm password {string}")
    public void theAdminFindsUniqueUserDetailsWithTheFollowingPasswordAndConfirmPassword(String password, String confirmPass) {
        this.password = password;
        this.confirmPassword = confirmPass;
        this.name = NameUtils.getUniqueName("Name");
        this.email = NameUtils.getUniqueName("Email") + "@gmail.com";
        this.type = NameUtils.getUniqueName("Type");
    }

    @And("the admin finds a role")
    public void theAdminFindsARole() {
        this.roleName = NameUtils.getUniqueName("Role");
        createRoleResponse = Role.createRole(roleName, jwtToken, "", "");
        this.roleId = SearchInResponse.getRoleIdByRoleName(Role.listRoles(jwtToken), roleName);
    }

    @Then("admin creates a user")
    public void adminCreatesAUser() {
        List<String> roleIdsList = Arrays.asList(roleId);
        this.addUserResponse = User.addUser(email, name, password, confirmPassword, "2024-10-01T06:43:32.790Z", roleIdsList, Objects.equals(userType, "") ? "" : Constants.AGEEK, jwtToken);
        PrintUtil.PrintSuccessLog("addUserResponse: " + addUserResponse);
    }

    @And("verifies to receive a status code of {int} after user creation")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterUserCreation(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(addUserResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after user creation")
    public void verifiesToReceiveAMessageAfterUserCreation(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(addUserResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("verifies its present in the user list")
    public void verifiesItsPresentInTheUserList() {
        this.userList = User.ListUsers(jwtToken);
        ListUserResponse = User.ListUsers(jwtToken);
        fetcheduserId = UserGenerics.findUserIdFromUsersList(ListUserResponse, name);
        if (fetcheduserId != null) {
            assert true; // Pass the test
        } else {
            assert false; // Fail the test
        }
    }


    @And("find user Id for user deletion")
    public void findUserIdForUserDeletion() {
        this.userId = UserGenerics.getUserDetailFromUserName(User.ListUsers(jwtToken), name, "userId");
    }

    @And("deletes the user later")
    public void deletesTheUserLater() {
        User.deleteUser(userId, jwtToken);
    }

    @And("enters a user email {string}")
    public void entersAUserEmail(String userEmail) {
        this.email = userEmail;
    }

    @And("verifies to receive an {string} with exception {string}")
    public void verifiesToReceiveAnWithException(String expectedExceptionMessage, String exception) {
        String actualExceptionMessage = ApiResponseUtils.getApiResponseException(addUserResponse, exception);
        assertEquals(expectedExceptionMessage, actualExceptionMessage, "Expected Exception: " + expectedExceptionMessage + ", but got: " + actualExceptionMessage);
    }

    @And("enters a super admin role")
    public void entersASuperAdminRole() {
        this.rolesList = Role.listRoles(jwtToken);
        this.roleId = RoleGenerics.getRoleDetailFromRoleName(rolesList, "SuperAdmin", "id");

    }

    @And("enters a roleId {string}")
    public void entersARoleId(String roleId) {
        this.roleId = roleId;
    }

    @And("the admin finds a second role")
    public void theAdminFindsASecondRole() {
        this.roleId2 = roleId;
        PrintUtil.PrintSuccessLog("roleId2: " + roleId2);
    }

    @Then("admin creates a user with multiple roles")
    public void adminCreatesAUserWithMultipleRoles() {
        PrintUtil.PrintSuccessLog("ROLE ID 1 : " + roleId);
        PrintUtil.PrintSuccessLog("ROLE ID 2 : " + roleId2);
        List<String> roleIdsList = Arrays.asList(roleId, roleId2);
        this.addUserResponse = User.addUser(email, name, password, confirmPassword, "2024-10-01T06:43:32.790Z", roleIdsList, Constants.AGEEK, jwtToken);
        PrintUtil.PrintSuccessLog("ADD USER RESPONSE : " + addUserResponse);
    }

    @And("the admin finds a second role distinct")
    public void theAdminFindsASecondRoleDistinct() {
        roleId2 = RoleGenerics.getLastRoleDetails(Role.listRoles(jwtToken), "id", "2");
    }

    @And("deletes the role after adding user")
    public void deletesTheRoleAfterAddingUser() {
        Role.deleteRole(roleId, jwtToken);
    }

    @And("deletes the second role after adding user")
    public void deletesTheSecondRoleAfterAddingUser() {
        Role.deleteRole(roleId2, jwtToken);
    }

    @And("enters a user type {string}")
    public void entersAUserType(String userType) {
        this.userType = userType;
    }

    @And("admin finds a user with an existing email")
    public void adminFindsAUserWithAnExistingEmail() {
//        this.email=UserGenerics.getLastUserDetails(User.ListUsers(jwtToken),"email","2");
        this.roleName = NameUtils.getUniqueName("Role1");
        createRoleResponse = Role.createRole(roleName, jwtToken, "", "");
        this.roleId = SearchInResponse.getRoleIdByRoleName(Role.listRoles(jwtToken), roleName);

//        this.roleName2 = NameUtils.getUniqueName("Role2");
//        createRoleResponse = Role.createRole(roleName,jwtToken);
//        this.roleId2 = SearchInResponse.getRoleIdByRoleName(Role.listRoles(jwtToken),roleName2);
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleId);
//        roleIds.add(roleId2);
        this.email = NameUtils.getUniqueType("email");
        PrintUtil.PrintSuccessLog("Adding user # 1: " + User.addUser(email, NameUtils.getUniqueName("name"), "Lockkeyz!23", "Lockkeyz!23", "2024-10-01T06:43:32.790Z", roleIds, "UserType", jwtToken));

    }

    @And("the admin finds an existing email")
    public void theAdminFindsAnExistingEmail() {
        email = UserGenerics.getLastUserDetails(User.ListUsers(jwtToken), "email", "3");
    }

}
