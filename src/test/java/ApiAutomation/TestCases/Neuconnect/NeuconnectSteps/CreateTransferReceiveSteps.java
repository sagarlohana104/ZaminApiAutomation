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

import javax.sql.rowset.spi.SyncResolver;
import javax.xml.transform.sax.SAXResult;

import static ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics.getJwtToken;

public class CreateTransferReceiveSteps {
    public String getJwtTokenUser() {
        return JwtTokenUser;
    }

    public void setJwtTokenUser(String jwtTokenUser) {
        JwtTokenUser = jwtTokenUser;
    }

    public String getAdminToken() {
        return AdminToken;
    }

    public void setAdminToken(String adminToken) {
        AdminToken = adminToken;
    }

    private String JwtTokenUser;
    private String AdminToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    private String  fromWareHouseCode;
    private String userId;

    public String getToWareHouseCode() {
        return toWareHouseCode;
    }

    public void setToWareHouseCode(String toWareHouseCode) {
        this.toWareHouseCode = toWareHouseCode;
    }

    public String getFromWareHouseCode() {
        return fromWareHouseCode;
    }

    public void setFromWareHouseCode(String fromWareHouseCode) {
        this.fromWareHouseCode = fromWareHouseCode;
    }

    private String toWareHouseCode;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    private String itemCode;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
    private String barCode;
    private String binCode;
    private String series;
    private String seriesName;
    private Scenario scenario;

    public String getListTransferReceiveResp() {
        return ListTransferReceiveResp;
    }

    public void setListTransferReceiveResp(String listTransferReceiveResp) {
        ListTransferReceiveResp = listTransferReceiveResp;
    }

    private String ListTransferReceiveResp;


    public String getCreateTRResponse() {
        return CreateTRResponse;
    }

    public void setCreateTRResponse(String createTRResponse) {
        CreateTRResponse = createTRResponse;
    }

    private String CreateTRResponse;


    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }




    @Given("A user has logged in access with CreateTransferReceive")
    public void aUserHasLoggedInAccessWithCreateTransferReceive() {
        setJwtTokenUser(getJwtToken(Login.login(Credentials.userEmail,Credentials.userPass)));
        PrintUtil.PrintSuccessLog(getJwtTokenUser());
    }

    @And("admin logged in access with CreateTransferReceive")
    public void adminLoggedInAccessWithCreateTransferReceive() {
        setAdminToken(getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getAdminToken());
    }

    @And("User id extract from user list to test itr to test CreateTransferReceive")
    public void userIdExtractFromUserListToTestItrToTestCreateTransferReceive() {
        setUserId(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListUsers(getAdminToken()),"data.users","userId","1"
        ));
        PrintUtil.PrintErrorLog("User id extract " + getUserId());
    }

    @And("Extract warehouse from warehouse list to test CreateTransferReceive")
    public void extractWarehouseFromWarehouseListToTestCreateTransferReceive() {
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

    @And("Extract to warehouse from warehouse list to test CreateTransferReceive")
    public void extractToWarehouseFromWarehouseListToTestCreateTransferReceive() {
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

    @And("Extract items code by user id from list all itemcode to test CreateTransferReceive")
    public void extractItemsCodeByUserIdFromListAllItemcodeToTestCreateTransferReceive() {
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
    }

    @And("Create direct TR with valid payload")
    public void createDirectTRWithValidPayload() {

        // Random quantity
        setQuantity(1);

        // Static series
        setSeries("200");

        // Unique series name every run
        setSeriesName("TR2026");
    }

    @And("CreateTransferReceive user id api called")
    public void createtransferreceiveUserIdApiCalled() {
       setCreateTRResponse(NeuconnectFunction.CreateTransferReceiveDirect(getJwtTokenUser(),getFromWareHouseCode(),getToWareHouseCode(),getItemCode(),getQuantity(),getBarCode(),getBinCode(),getSeries(),getSeriesName()));
       PrintUtil.PrintSuccessLog("Response" + getCreateTRResponse());
    }

    @And("isApiHandled is verified as {string} to test CreateTransferReceive")
    public void isapihandledIsVerifiedAsToTestCreateTransferReceive(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getCreateTRResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list CreateTransferReceive")
    public void isrequestsuccessIsVerifiedAsToTestListCreateTransferReceive(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getCreateTRResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list CreateTransferReceive")
    public void statuscodeIsVerifiedAsStatusCodeToTestListCreateTransferReceive(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getCreateTRResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list CreateTransferReceive")
    public void messageIsVerifiedToTestListCreateTransferReceive(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getCreateTRResponse(),scenario.getName());
    }

    @And("list TRS api called")
    public void listTRSApiCalled() {
        setListTransferReceiveResp(NeuconnectFunction.ListAllTransferReceive(getAdminToken()));
        PrintUtil.PrintSuccessLog(getListTransferReceiveResp());

    }
}
