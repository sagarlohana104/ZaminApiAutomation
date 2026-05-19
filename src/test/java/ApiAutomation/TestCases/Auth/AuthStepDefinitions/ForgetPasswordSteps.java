package ApiAutomation.TestCases.Auth.AuthStepDefinitions;

import ApiAutomation.Neuconnect.POM.Auth.AuthFunctions;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.User;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Asserts.AssertUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ForgetPasswordSteps {

    private String jwtToken,email,forgetPasswordResponse;
    private Scenario scenario;

    // Inject Scenario object
    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getEmail() {
        return email;
    }

    public String getForgetPasswordResponse() {
        return forgetPasswordResponse;
    }

    public void setForgetPasswordResponse(String forgetPasswordResponse) {
        this.forgetPasswordResponse = forgetPasswordResponse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
    }

    @When("the user has a valid userEmail")
    public void theUserHasAValidUserEmail() {
        setEmail(
                GenericExtractorsValidators.getLastDetail(User.ListUsers(getJwtToken()),"data.users","email","7")
        );
        PrintUtil.PrintSuccessLog("EMAIL : "+getEmail());
    }

    @And("the forgetPassword api is called")
    public void theForgetPasswordApiIsCalled() {
        setForgetPasswordResponse(
                AuthFunctions.ForgetPassword(getEmail(),"string")
        );
        PrintUtil.PrintSuccessLog("FORGET PASSWORD RESPONSE : "+ getForgetPasswordResponse());
    }

    @Then("verifies to receive isApiHandled {string} to test forget password")
    public void verifiesToReceiveIsApiHandledToTestForgetPassword(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getForgetPasswordResponse(),scenario.getName());
    }

    @And("verifies to receive isRequestSuccess {string} to test forget password")
    public void verifiesToReceiveIsRequestSuccessToTestForgetPassword(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getForgetPasswordResponse(),scenario.getName());
    }

    @And("verifies to receive statusCode {int} to test forget password")
    public void verifiesToReceiveStatusCodeStatusCodeToTestForgetPassword(int statusCode) {
        AssertUtils.verifiesToReceiveStatusCode(statusCode,getForgetPasswordResponse(),scenario.getName());
    }

    @And("verifies to receive message {string} to test forget password")
    public void verifiesToReceiveMessageToTestForgetPassword(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getForgetPasswordResponse(),scenario.getName());
    }

    @When("the user has an ivalid userEmail")
    public void theUserHasAnIvalidUserEmail() {
        setEmail(NameUtils.getUniqueName("EMAIL")+"@gmail.com");
    }

    @And("verifies to receive exception {string} with {string} to test forget password")
    public void verifiesToReceiveExceptionWithToTestForgetPassword(String arg0, String arg1) {
        AssertUtils.verifiesToReceiveException(arg0,arg1,getForgetPasswordResponse(),scenario.getName());
    }
}
