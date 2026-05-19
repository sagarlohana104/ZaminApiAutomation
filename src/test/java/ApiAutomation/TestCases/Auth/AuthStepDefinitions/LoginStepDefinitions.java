package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginStepDefinitions {
    private String jwtToken ;
    private String refreshToken ;
    private String loginResponse ;
    private String email ;
    private String password;


    @Given("the super admin is logged in with following credentials {string} {string}")
    public void theSuperAdminIsLoggedInWithFollowingCredentials(String email, String password)
    {
        this.email=email;
        this.password=password;
    }

    @When("the login api is called")
    public void theLoginApiIsCalled() {
        this.loginResponse= Login.login(email,password);
        PrintUtil.PrintSuccessLog("LOGIN RESPONSE : "+ loginResponse);
    }


    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBeStatusCode(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(loginResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);


    }

    @And("the response message should be {string}")
    public void theResponseMessageShouldBe(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(loginResponse);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);

    }
}
