package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnectendpoints;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Asserts.AssertUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.PortUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import javax.sound.sampled.Port;
import java.security.Principal;

public class AddwarehousetouserSteps {
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public String getNormalWarehouseIds() {
        return normalWarehouseIds;
    }

    public void setNormalWarehouseIds(String normalWarehouseIds) {
        this.normalWarehouseIds = normalWarehouseIds;
    }

    public String getReceiverWarehouseIds() {
        return receiverWarehouseIds;
    }

    public void setReceiverWarehouseIds(String receiverWarehouseIds) {
        this.receiverWarehouseIds = receiverWarehouseIds;
    }

    private String normalWarehouseIds;
    private String receiverWarehouseIds;

    public String getAddwarehousetouserresponse() {
        return addwarehousetouserresponse;
    }

    public void setAddwarehousetouserresponse(String addwarehousetouserresponse) {
        this.addwarehousetouserresponse = addwarehousetouserresponse;
    }

    private String addwarehousetouserresponse;

    private Scenario scenario;
    @Before
    public void beforescenario(Scenario scenario){
        this.scenario=scenario;
    }

    @Given("A user has logged in access with list all addwarehousetouser")
    public void aUserHasLoggedInAccessWithListAllAddwarehousetouser() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());

    }

    @And("User id extract from user list")
    public void userIdExtractFromUserList() {
        setUserId(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListUsers(getJwtToken()),"data.users","userId","1"
        ));
        PrintUtil.PrintSuccessLog("User id extract from user list " + getUserId());
    }

    @When("add warehouse to user api called")
    public void addWarehouseToUserApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setAddwarehousetouserresponse(NeuconnectFunction.AddWarehousetoUser(getJwtToken(),getUserId(),getNormalWarehouseIds(),getReceiverWarehouseIds()));
        PrintUtil.PrintSuccessLog(getAddwarehousetouserresponse());
    }

    @And("isApiHandled is verified as {string} to test addwarehousetouser")
    public void isapihandledIsVerifiedAsToTestAddwarehousetouser(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getAddwarehousetouserresponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test addwarehousetouser")
    public void isrequestsuccessIsVerifiedAsToTestAddwarehousetouser(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getAddwarehousetouserresponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list addwarehousetouser")
    public void statuscodeIsVerifiedAsStatusCodeToTestListAddwarehousetouser(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getAddwarehousetouserresponse(),scenario.getName());
    }

    @And("message is verified {string} to test list addwarehousetouser")
    public void messageIsVerifiedToTestListAddwarehousetouser(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getAddwarehousetouserresponse(),scenario.getName());
    }

    @And("Extract warehouse from warehouse list")
    public void extractWarehouseFromWarehouseList() {
        //PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setNormalWarehouseIds(GenericExtractorsValidators.getLastDetail(NeuconnectFunction.ListAllWarehouse(getJwtToken()),"data.data","id","1"));

        PrintUtil.PrintSuccessLog("Extract from warehouse from the list " +getNormalWarehouseIds());
    }

    @And("extact warehouse to from warehouse list")
    public void extactWarehouseToFromWarehouseList() {
        //PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setReceiverWarehouseIds(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListAllWarehouse(getJwtToken()),"data.data","id","1"
        ));
        PrintUtil.PrintSuccessLog("Extract to warehouse from this list" + getReceiverWarehouseIds());
    }
}
