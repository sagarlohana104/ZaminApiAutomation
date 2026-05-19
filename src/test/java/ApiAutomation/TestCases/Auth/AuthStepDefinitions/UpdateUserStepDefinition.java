package ApiAutomation.TestCases.Auth.AuthStepDefinitions;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.User;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.UserGenerics;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateUserStepDefinition {
    private String loginResponse;
    private String jwtToken;
    private String userId ;
    private String userEmail ;
    private String userName ;
    private String updateUserResponse ;
    private String roleId ;


    @Given("the super admin is logged in with following credentials to update a user")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsAndToUpdateAUser() {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }


    @When("admin finds a user to update")
    public void adminFindsAUserToUpdate() {
        this.userId = UserGenerics.findUserIdFromUsersList(User.ListUsers(jwtToken), Constants.UserName);
    }

    @And("creates unique email and name")
    public void createsUniqueEmailAndName() {
        this.userName= NameUtils.getUniqueName("Name");
        this.userEmail=NameUtils.getUniqueName("Email") + "@gmail.com";
    }

    @Then("updates a user")
    public void updatesAUser() {
        this.updateUserResponse=User.updateUser(userId,userEmail,userName,jwtToken);
//        System.out.println(updateUserResponse);

    }

    @And("verifies to receive a status code of {int} after updating a user")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterUpdatingAUser(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(updateUserResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after updating a user")
    public void verifiesToReceiveAMessageAfterUpdatingAUser(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(updateUserResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("verifies the list contains the updated user")
    public void verifiesTheListContainsTheUpdatedUser() {
        assertTrue(UserGenerics.findUserFromUsersList(User.ListUsers(jwtToken),userName,"name"));
        assertTrue(UserGenerics.findUserFromUsersList(User.ListUsers(jwtToken),userEmail,"email"));

    }

    @And("finds duplicate email and unique name")
    public void findsDuplicateEmailAndUniqueName() {
        this.userName=NameUtils.getUniqueName("Name");
        this.userEmail=UserGenerics.getLastUserDetails(User.ListUsers(jwtToken),"email","5");
//        System.out.println("userEmail: "+userEmail);
    }

    @And("puts name as {string}")
    public void putsNameAs(String name) {
        this.userName=name;
    }

    @And("verifies the exception message {string} for the exception {string} for updating user")
    public void verifiesTheExceptionMessageForTheExceptionForUpdatingUser(String expectedExceptionMessage, String Exception) {
        String actualExceptionMessage = ApiResponseUtils.getApiResponseException(updateUserResponse,Exception);
        assertEquals(expectedExceptionMessage, actualExceptionMessage, "Expected Exception: " + expectedExceptionMessage + ", but got: " + actualExceptionMessage);
    }

    @And("puts user id as {string}")
    public void putsUserIdAs(String userId) {
        this.userId = userId;
    }

    @And("puts email as {string}")
    public void putsEmailAs(String email) {
        this.userEmail=email;
    }

    @When("admin finds a role to assign to new user")
    public void adminFindsARoleToAssignToNewUser() {
        this.roleId= RoleGenerics.getLastRoleDetails(Role.listRoles(jwtToken),"roleId","2");
    }

    @And("admin creates a new user")
    public void adminCreatesANewUser() {
        this.userName=NameUtils.getUniqueName("Name");
        this.userEmail=NameUtils.getUniqueName("Email")+"@gmail.com";
        List<String> role = Arrays.asList(this.roleId);
        User.addUser(userEmail,userName,"Lockkeyz!23","Lockkeyz!23","2024-10-01T06:43:32.790Z" , role,NameUtils.getUniqueName("Type"),jwtToken);
    }

    @And("deletes the user")
    public void deletesTheUser() {
        this.userId=UserGenerics.getUserDetailFromUserName(User.ListUsers(jwtToken),userName,"userId");
        User.deleteUser(userId,jwtToken);
    }


    @When("admin finds a random user to update")
    public void adminFindsARandomUserToUpdate() {
        this.userId = UserGenerics.getLastUserDetails(User.ListUsers(jwtToken),"userId","3");

    }
}
