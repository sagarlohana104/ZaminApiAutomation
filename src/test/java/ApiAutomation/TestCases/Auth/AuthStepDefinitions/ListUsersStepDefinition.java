package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.User;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.UserGenerics;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class ListUsersStepDefinition {

    private String loginResponse;
    private String jwtToken;
    private String listUsersResponse ;
    private String email;
    private String name ;
    private String type;
    private String roleId;
    private String addUserResponse;
    private String userId;


    @Given("the super admin is logged in with following credentials to list users")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToListUsers() {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }

    @When("the admin runs list users endpoint")
    public void theAdminRunsListUsersEndpoint() {
        this.listUsersResponse= User.ListUsers(jwtToken);
    }

    @And("find unique user details")
    public void findUniqueUserDetails() {
        List<String> roleIdsList = Arrays.asList(roleId);
        this.addUserResponse= User.addUser(email,name,"Lockkeyz@Lockkeyz.com","Lockkeyz!23","2024-10-01T06:43:32.790Z",roleIdsList,type,jwtToken);
    }

    @And("find role")
    public void findRole() {
        this.roleId= RoleGenerics.getLastRoleDetails(Role.listRoles(jwtToken),"roleId","4");
    }


    @And("create a new unique user")
    public void createANewUniqueUser() {
        List<String> roleIdsList = Arrays.asList(roleId);
        this.addUserResponse= User.addUser(email,name,"Pos!2345","Pos!2345","2024-10-01T06:43:32.790Z",roleIdsList,type,jwtToken);
    }

    @And("extract userId")
    public void extractUserId() {
        this.userId= UserGenerics.getUserDetailFromUserName(User.ListUsers(jwtToken),name,"userId");
    }

    @And("delete user")
    public void deleteUser() {
        User.deleteUser(userId,jwtToken);
    }

    @Then("verifies to receive a status code of {int} after user listing")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterUserListing(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(listUsersResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after user listing")
    public void verifiesToReceiveAMessageAfterUserListing(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(listUsersResponse);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("to verify the deleted user is not present in the user list")
    public void toVerifyTheDeletedUserIsNotPresentInTheUserList() {
        Assert.assertFalse(UserGenerics.findUserFromUsersList(User.ListUsers(jwtToken),name,"name"));
    }

    @Given("a user is logged in with access to list company api")
    public void aUserIsLoggedInWithAccessToListCompanyApi() {


    }

}
