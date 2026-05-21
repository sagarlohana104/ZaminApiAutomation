package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
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

import java.security.Principal;

public class CreateGoodIssueSteps {
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    private String adminToken;

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getUoM() {
        return uoM;
    }

    public void setUoM(String uoM) {
        this.uoM = uoM;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBinCode() {
        return binCode;
    }

    public void setBinCode(String binCode) {
        this.binCode = binCode;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
    private String whsCode;
    private String itemCode;
    private int quantity;
    private String resource;
    private String uoM;
    private String barCode;
    private String binCode;
    private String series;
    private String seriesName;
    private Scenario scenario;

    public String getCreateGoodissueResp() {
        return CreateGoodissueResp;
    }

    public void setCreateGoodissueResp(String createGoodissueResp) {
        CreateGoodissueResp = createGoodissueResp;
    }

    private String CreateGoodissueResp;


    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }

    @Given("User login for create goods issue")
    public void userLoginForCreateGoodsIssue() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.userEmail,Credentials.userPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());

    }

    @And("Admin login for create goods issue")
    public void adminLoginForCreateGoodsIssue() {
        setAdminToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getAdminToken());

    }

    @And("Extract warehouse code from warehouse list for goods issue")
    public void extractWarehouseCodeFromWarehouseListForGoodsIssue() {

                setWhsCode(GenericExtractorsValidators.getLastDetail(
                        NeuconnectFunction.ListAllWarehouse(getAdminToken()),
                        "data.data",
                        "whsCode",
                        "2"
                )
        );

        PrintUtil.PrintSuccessLog("TO WH: " + getWhsCode());
    }

    @And("Extract item code from item list for goods issue")
    public void extractItemCodeFromItemListForGoodsIssue() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));

        String response = NeuconnectFunction.listAllItemCodebyUserId(
                getAdminToken(),
                getUserId()
        );
        System.out.println("ITEM API RESPONSE: " + response);

        String randomIndex = String.valueOf((int)(Math.random() * 5) + 1);

        setItemCode(
                GenericExtractorsValidators.getLastDetail(
                        response,
                        "data.items",
                        "itemCode",
                        randomIndex
                )
        );

        PrintUtil.PrintSuccessLog("ITEM CODE: " + getItemCode());
    }

    @And("Set payload for create goods issue")
    public void setPayloadForCreateGoodsIssue() {
        setQuantity(1);
        setUoM("Manual");
        setBarCode("");
        setBinCode("");
        setSeries("198");
        setSeriesName("Adj26");
    }

    @And("Create goods issue api called")
    public void createGoodsIssueApiCalled() {
        setCreateGoodissueResp(NeuconnectFunction.CreateGoodsIssue(getJwtToken(),getWhsCode(),getItemCode(),getQuantity(),getUoM(),getBarCode(),getBinCode(),getSeries(),getSeriesName()));
        PrintUtil.PrintSuccessLog("Response " + getCreateGoodissueResp());


    }

    @And("isApiHandled is verified as {string} to test goods issue")
    public void isapihandledIsVerifiedAsToTestGoodsIssue(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getCreateGoodissueResp(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list goods issue")
    public void isrequestsuccessIsVerifiedAsToTestListGoodsIssue(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getCreateGoodissueResp(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list goods issue")
    public void statuscodeIsVerifiedAsStatusCodeToTestListGoodsIssue(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getCreateGoodissueResp(),scenario.getName());

    }

    @And("message is verified {string} to test list goods issue")
    public void messageIsVerifiedToTestListGoodsIssue(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getCreateGoodissueResp(),scenario.getName());
    }

    @And("Extract user id from user list for item code")
    public void extractUserIdFromUserListForItemCode() {
        setUserId(
                GenericExtractorsValidators.getLastDetail(
                        NeuconnectFunction.ListUsers(getAdminToken()),
                        "data.users",
                        "userId",
                        "1"
                )
        );

        PrintUtil.PrintSuccessLog("USER ID: " + getUserId());
    }
}

