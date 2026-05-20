package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Asserts.AssertUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.NameUtils;
import ApiAutomation.Neuconnect.Utils.PortUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import javax.sound.sampled.Port;

public class CreateITRSteps {

    private String jwtToken;
    private String adminToken;

    private String fromWareHouseCode;
    private String toWareHouseCode;
    private String itemCode;
    private int quantity;
    private String uom;
    private String barCode;
    private String binCode;
    private String series;
    private String seriesName;
    private String userId;

    private String createITRResponse;
    private Scenario scenario;

    // ---------------- GETTERS & SETTERS ----------------

    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }

    public String getAdminToken() { return adminToken; }
    public void setAdminToken(String adminToken) { this.adminToken = adminToken; }

    public String getFromWareHouseCode() { return fromWareHouseCode; }
    public void setFromWareHouseCode(String fromWareHouseCode) { this.fromWareHouseCode = fromWareHouseCode; }

    public String getToWareHouseCode() { return toWareHouseCode; }
    public void setToWareHouseCode(String toWareHouseCode) { this.toWareHouseCode = toWareHouseCode; }

    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUom() { return uom; }
    public void setUom(String uom) { this.uom = uom; }

    public String getBarCode() { return barCode; }
    public void setBarCode(String barCode) { this.barCode = barCode; }

    public String getBinCode() { return binCode; }
    public void setBinCode(String binCode) { this.binCode = binCode; }

    public String getSeries() { return series; }
    public void setSeries(String series) { this.series = series; }

    public String getSeriesName() { return seriesName; }
    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCreateITRResponse() { return createITRResponse; }
    public void setCreateITRResponse(String createITRResponse) { this.createITRResponse = createITRResponse; }

    // ---------------- SCENARIO ----------------

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getGetListITRResponse() {
        return GetListITRResponse;
    }

    public void setGetListITRResponse(String getListITRResponse) {
        GetListITRResponse = getListITRResponse;
    }

    private String GetListITRResponse;


    // ---------------- LOGIN ----------------

    @Given("A user has logged in access with CreateInventoryTransferRequest")
    public void loginUser() {
        setJwtToken(AuthGenerics.getJwtToken(
                Login.login(Credentials.userEmail, Credentials.userPass)
        ));

        PrintUtil.PrintSuccessLog("USER TOKEN: " + getJwtToken());
    }

    @And("admin logged in access with create itr")
    public void loginAdmin() {
        setAdminToken(AuthGenerics.getJwtToken(
                Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass)
        ));

        PrintUtil.PrintSuccessLog("ADMIN TOKEN: " + getAdminToken());
    }

    // ---------------- BASIC DATA ----------------

    @And("Create itr with valid payload")
    public void setBasicData() {

        setUom("Manual");

        // Random quantity
        setQuantity((int) (Math.random() * 100) + 1);

        // Static series
        setSeries("199");

        // Unique series name every run
        setSeriesName("TR2026");
    }

    // ---------------- WAREHOUSE ----------------

    @And("Extract warehouse from warehouse list to test create itr")
    public void extractFromWarehouse() {

        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));

        setFromWareHouseCode(
                GenericExtractorsValidators.getLastDetail(
                        NeuconnectFunction.ListAllWarehouse(getAdminToken()),
                        "data.data",
                        "whsCode",
                        "1"
                )
        );

        PrintUtil.PrintSuccessLog("FROM WH: " + getFromWareHouseCode());
    }

    @And("Extract to warehouse from warehouse list to test create itr")
    public void extractToWarehouse() {

        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));

        setToWareHouseCode(
                GenericExtractorsValidators.getLastDetail(
                        NeuconnectFunction.ListAllWarehouse(getAdminToken()),
                        "data.data",
                        "whsCode",
                        "2"
                )
        );

        PrintUtil.PrintSuccessLog("TO WH: " + getToWareHouseCode());
    }
    // ---------------- ITEM ----------------

    @And("Extract items code by user id from list all itemcode to test create itr")
    public void extractItemCode() {

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

    @And("CreateInventoryTransferRequest user id api called")
    public void createITR() {
    PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setCreateITRResponse(
                NeuconnectFunction.CreateInventoryTransferRequest(
                        getJwtToken(),
                        getFromWareHouseCode(),
                        getToWareHouseCode(),
                        getItemCode(),
                        getQuantity(),
                        getUom(),
                        getBarCode(),
                        getBinCode(),
                        getSeries(),
                        getSeriesName()
                )
        );

        PrintUtil.PrintSuccessLog("RESPONSE: " + getCreateITRResponse());
    }

    // ---------------- ASSERTIONS ----------------

    @And("isApiHandled is verified as {string} to test CreateInventoryTransferRequest")
    public void verifyApiHandled(String expected) {
        AssertUtils.verifiesToReceiveIsApiHandled(expected, getCreateITRResponse(), scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list CreateInventoryTransferRequest")
    public void verifyRequestSuccess(String expected) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(expected, getCreateITRResponse(), scenario.getName());
    }

    @And("statusCode is verified as {int} to test list CreateInventoryTransferRequest")
    public void verifyStatusCode(int expected) {
        AssertUtils.verifiesToReceiveStatusCode(expected, getCreateITRResponse(), scenario.getName());
    }

    @And("message is verified {string} to test list CreateInventoryTransferRequest")
    public void verifyMessage(String expected) {
        AssertUtils.verifiesToReceiveMessage(expected, getCreateITRResponse(), scenario.getName());
    }

    @And("CreateInventoryTransferRequest user id  api called")
    public void createinventorytransferrequestUserIdApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setCreateITRResponse(NeuconnectFunction.CreateInventoryTransferRequest(getJwtToken(),getFromWareHouseCode(),getToWareHouseCode(),getItemCode(),getQuantity(),getUom(),getBarCode(),getBinCode(),getSeries(),getSeriesName()));
        PrintUtil.PrintErrorLog(getCreateITRResponse());
    }

    @And("User id extract from user list to test itr")
    public void userIdExtractFromUserListToTestItr() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setUserId(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListUsers(getAdminToken()),"data.users","userId","1"
        ));
        PrintUtil.PrintErrorLog("User id extract " + getUserId());
    }

    @And("Call itr list api")
    public void callItrListApi() {
        PrintUtil.PrintErrorLog(envConfig.getEnv("Zamin_PORT"));
        setGetListITRResponse(NeuconnectFunction.ListAllITRS(getAdminToken()));
        PrintUtil.PrintSuccessLog(getGetListITRResponse());
    }
}