package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.User;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.UserGenerics;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import ApiAutomation.Neuconnect.Utils.SearchInResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class DeleteUserStepDefinitions {
    private String loginResponse;
    private String jwtToken;
    private String password;
    private String email;
    private String name ;
    private String type;
    private String confirmPassword;
    private String roleId,roleName;
    private String addUserResponse;
    private String userId;
    private String deleteUserResponse;
    private String createRoleResponse,ListRolesResponse;

    @Given("the super admin is logged in with following credentials to delete user")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToDeleteUser() {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }

    @When("the admin has unique user details with the following password {string} and confirm password {string}")
    public void theAdminHasUniqueUserDetailsWithTheFollowingPasswordAndConfirmPassword(String userPass, String confirmUserPass) {
        this.password=userPass;
        this.confirmPassword =confirmUserPass;
        this.name= NameUtils.getUniqueName("Name");
        this.email=NameUtils.getUniqueName("Email") + "@gmail.com";
        this.type= NameUtils.getUniqueName("Type");
    }

    @And("the admin finds a role for user creation")
    public void theAdminFindsARoleForUserCreation() {
        this.roleName = NameUtils.getUniqueName("RoleName");
        createRoleResponse = Role.createRole(roleName,jwtToken,"","");
        ListRolesResponse = Role.listRoles(jwtToken);

        this.roleId = SearchInResponse.getRoleIdByRoleName(ListRolesResponse,roleName);


    }

    @And("admin creates a user so we can delete that later")
    public void adminCreatesAUserSoWeCanDeleteThatLater() {
        List<String> roleIdsList = Arrays.asList(roleId);
        this.addUserResponse= User.addUser(email,name,password,confirmPassword,"2024-10-01T06:43:32.790Z",roleIdsList, Constants.AGEEK,jwtToken);
    }

    @And("admin extracts user id")
    public void adminExtractsUserId() {
        this.userId= UserGenerics.getUserDetailFromUserName(User.ListUsers(jwtToken),name,"userId");
    }

    @Then("admin deletes the user")
    public void adminDeletesTheUser() {
        this.deleteUserResponse=User.deleteUser(userId,jwtToken);
        System.out.println("deleteUserResponse: "+deleteUserResponse);
    }

    @And("verifies to receive a status code of {int} after user deletion")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterUserDeletion(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(deleteUserResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }



    @And("verifies to receive a message {string} after user deletion")
    public void verifiesToReceiveAMessageAfterUserDeletion(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(deleteUserResponse);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("admin has userId {string}")
    public void adminHasUserId(String userId) {
        this.userId = userId;
    }

    @And("verifies to receive an {string} with exception {string} after user deletion")
    public void verifiesToReceiveAnWithExceptionAfterUserDeletion(String expectedExceptionMessage, String exception) {
        String actualExceptionMessage = ApiResponseUtils.getApiResponseException(deleteUserResponse,exception);
        Assertions.assertEquals(expectedExceptionMessage, actualExceptionMessage, "Expected Exception: " +expectedExceptionMessage + ", but got: " + actualExceptionMessage);
    }

    @And("admin has a super admin user Id")
    public void adminHasASuperAdminUserId() {
        this.userId=UserGenerics.getUserDetailFromUserName(User.ListUsers(jwtToken),Constants.UserName,"userId");

    }

}
