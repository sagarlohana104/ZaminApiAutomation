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
import org.junit.jupiter.api.Assertions;

public class GetRoleStepDefinition {
    private String loginResponse;
    private String jwtToken;
    private String roleName;
    private String roleId ;
    private String addRoleResponse ;
    private String roleIdResponse,ListRolesResponse;

    @Given("the super admin is logged in with following credentials to retrieve roles")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsToRetrieveRoles()
    {
        this.loginResponse= Login.login(Credentials.AuthEmail,Credentials.AuthPass);
        this.jwtToken= AuthGenerics.getJwtToken(loginResponse);
    }

    @When("the admin finds a unique role name to add for successful retrieval later")
    public void theAdminFindsAUniqueRoleNameToAddForSuccessfulRetrievalLater() {
        this.roleName= NameUtils.getUniqueName("Role");
    }

    @And("then the admin calls the add role endpoint")
    public void thenTheAdminCallsTheAddRoleEndpoint() {
        this.addRoleResponse = Role.createRole(roleName,jwtToken,"","");
    }

    @And("then stores the roleId")
    public void thenStoresTheRoleId() {
        ListRolesResponse = Role.listRoles(jwtToken);
        this.roleId = SearchInResponse.getRoleIdByRoleName(ListRolesResponse,roleName);
    }

    @Then("successfully retrieves the data of the roleId")
    public void successfullyRetrievesTheDataOfTheRoleId() {
        this.roleIdResponse= Role.getRoleById(roleId,jwtToken);
    }

    @And("verifies to receive a status code of {int} by retrieval of role details")
    public void verifiesToReceiveAStatusCodeOfStatusCodeByRetrievalOfRoleDetails(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(roleIdResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("verifies to receive a message {string} by retrieval of role details")
    public void verifiesToReceiveAMessageByRetrievalOfRoleDetails(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(roleIdResponse);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

//    @And("verifies the data retrieved are valid")
//    public void verifiesTheDataRetrievedAreValid() {
//        System.out.println(roleIdResponse);
//        String actualRoleId = ApiResponseUtils.getApiResponseDataDetails(roleIdResponse,"roleId");
//        String actualRoleName = ApiResponseUtils.getApiResponseDataDetails(roleIdResponse,"roleName");
////        System.out.println(actualRoleName+"-"+actualRoleId);
////        assertEquals(roleId,actualRoleId , "Expected Message: " + roleId + ", but got: " + actualRoleId);
////        assertEquals(roleName,actualRoleName, "Expected Message: " + roleName + ", but got: " + actualRoleName);
//    }

    @And("delete that role in the end")
    public void deleteThatRoleInTheEnd() {
        Role.deleteRole(roleId,jwtToken);
    }

    @When("the admin has a roleId {string}")
    public void theAdminHasARoleId(String roleId) {
        this.roleId = roleId;
    }
}
