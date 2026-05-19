package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateRoleStepDefinitions {
    public static String loginResponse,jwtToken,name,tag,addActionResponse;
    public static String deleteRoleResponse,listRolesResponse,createRoleResponse,roleId,roleName;


    @Given("the super admin is logged in with following credentials to create a role")
    public void theSuperAdminIsLoggedInWithFollowingCredentialstoCreateRole() {
        loginResponse = Login.login(Credentials.AuthEmail, Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
        System.out.println("LoginResposne: "+loginResponse);
    }

    @When("admin calls createRole api to create a random role")
    public void adminCallsCreateRoleApiToCreateARandomRole() {
        roleName = NameUtils.getUniqueName("Role");
        createRoleResponse = Role.createRole(roleName,jwtToken,"","");
    }

    @Then("verifies to receive a status code of {int} after creation of an role")
    public void verifiesToReceiveAStatusCodeOfStatusCodeAfterCreationOfAnRole(int expectedStatusCode ) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(createRoleResponse);
        assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} after an creation of an role")
    public void verifiesToReceiveAMessageAfterAnCreationOfAnRole(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(createRoleResponse);
        assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @And("admin deletes a role")
    public void adminDeletesARole() {
        listRolesResponse = Role.listRoles(jwtToken);
        roleId = RoleGenerics.getIdByRoleName(listRolesResponse,roleName);
        deleteRoleResponse = Role.deleteRole(roleId,jwtToken);
        System.out.println("deleteRoleResponse"+deleteRoleResponse);
    }
}
