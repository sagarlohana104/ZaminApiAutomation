package ApiAutomation.TestCases.Auth.AuthStepDefinitions;


import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.RefreshToken;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class RefreshTokenStepDefinition {
    private String email ;
    private String password;
    private String loginResponse;
    private String accessToken ;
    private String refreshToken ;
    private String refreshTokenResponse ;

    @Given("the super admin is logged in with following credentials r")
    public void theSuperAdminIsLoggedInWithFollowingCredentialsR()
    {
        this.email= Credentials.AuthEmail;
        this.password=Credentials.AuthPass;
    }

    @When("the login api is called.")
    public void theLoginApiIsCalled() {
        this.loginResponse= Login.login(email,password);
//        System.out.println(loginResponse);
    }

    @And("the accessToken and refreshToken are retrieved")
    public void theAccessTokenAndRefreshTokenAreRetrieved() {
        this.accessToken= AuthGenerics.getJwtToken(loginResponse);
        this.refreshToken=AuthGenerics.getRefreshToken(loginResponse);
    }

    @Then("the refresh token api is called with valid access token and invalid refresh token")
    public void theRefreshTokenApiIsCalledWithValidAccessTokenAndInvalidRefreshToken() {
        this.refreshTokenResponse= RefreshToken.refreshToken(accessToken,"sjkfkfhjkdsd");
        System.out.println("refreshTokenResponse: "+refreshTokenResponse);
    }

    @And("the status code received is {int}")
    public void theStatusCodeReceivedIsStatusCode(int expectedStatusCode) {
        int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(refreshTokenResponse);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
    }

    @And("the response message received should be {string}")
    public void theResponseMessageReceivedShouldBe(String expectedMessage) {
        String actualMessage = ApiResponseUtils.getApiResponseMessage(refreshTokenResponse);
        Assertions.assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);
    }

    @Then("the refresh token api is called with valid refresh token and invalid access token")
    public void theRefreshTokenApiIsCalledWithValidRefreshTokenAndInvalidAccessToken() {
        this.refreshTokenResponse= RefreshToken.refreshToken("sdjshdkhsd",refreshToken);
    }

    @Then("the refresh token api is called with opposite refresh token and opposite access token")
    public void theRefreshTokenApiIsCalledWithOppositeRefreshTokenAndOppositeAccessToken() {
        this.refreshTokenResponse= RefreshToken.refreshToken(refreshToken,accessToken);

    }
}
