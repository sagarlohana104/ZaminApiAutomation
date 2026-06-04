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

import javax.sql.rowset.spi.SyncResolver;

public class CreateSalesOrderSteps {
    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public String getAdminToken() {
        return AdminToken;
    }

    public void setAdminToken(String adminToken) {
        AdminToken = adminToken;
    }

    private String JwtToken;
    private String AdminToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    private String userId;
    private String whsCode;
    private String itemCode;
    private Scenario scenario;

    public String getListAllSalesOrderResponse() {
        return ListAllSalesOrderResponse;
    }

    public void setListAllSalesOrderResponse(String listAllSalesOrderResponse) {
        ListAllSalesOrderResponse = listAllSalesOrderResponse;
    }

    private String ListAllSalesOrderResponse;


    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }

    public String getCustomerReferenceId() {
        return customerReferenceId;
    }

    public void setCustomerReferenceId(String customerReferenceId) {
        this.customerReferenceId = customerReferenceId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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

    public int getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(int deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    private String customerReferenceId;
    private double quantity;
    private String barCode;
    private String binCode;
    private int deliveryDate;

    public String getUoM() {
        return uoM;
    }

    public void setUoM(String uoM) {
        this.uoM = uoM;
    }

    private String uoM;

    public String getCreateSalesOrderResponse() {
        return CreateSalesOrderResponse;
    }

    public void setCreateSalesOrderResponse(String createSalesOrderResponse) {
        CreateSalesOrderResponse = createSalesOrderResponse;
    }

    private String CreateSalesOrderResponse;



    @Given("A user has logged in access with CreateSalesOrder")
    public void aUserHasLoggedInAccessWithCreateSalesOrder() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.userEmail,Credentials.userPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @And("admin logged in access with CreateSalesOrder")
    public void adminLoggedInAccessWithCreateSalesOrder() {
        setAdminToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getAdminToken());
    }

    @And("User id extract from user list to test CreateSalesOrder")
    public void userIdExtractFromUserListToTestCreateSalesOrder() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setUserId(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListUsers(getAdminToken()),"data.users","userId","3"
        ));
    }

    @And("Extract warehouse from warehouse list to test CreateSalesOrder")
    public void extractWarehouseFromWarehouseListToTestCreateSalesOrder() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setWhsCode(GenericExtractorsValidators.getLastDetail(
                NeuconnectFunction.ListAllWarehouse(getAdminToken()),"data.data",
                "whsCode",
                "1"
        ));
    }

    @And("Extract item code from item list to test CreateSalesOrder")
    public void extractItemCodeFromItemListToTestCreateSalesOrder() {
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

    @And("CreateSalesOrder  with valid payload")
    public void createsalesorderWithValidPayload() {
        setQuantity(12);
        setUoM("Manual");
        setBarCode("");
        setBinCode("");
        setDeliveryDate(1);


    }

    @And("Extract customerReferenceId from customer list using user id")
    public void extractCustomerReferenceIdFromCustomerListUsingUserId() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));

        String response = NeuconnectFunction.ListAllCustomersOfUser(
                getAdminToken(),
                getUserId()
        );
        System.out.println("ITEM API RESPONSE: " + response);

        String randomIndex = String.valueOf((int)(Math.random() * 5) + 1);

        setCustomerReferenceId(
                GenericExtractorsValidators.getLastDetail(
                        response,
                        "data.customers",
                        "vendorCode",
                        randomIndex
                )
        );

        PrintUtil.PrintSuccessLog("Customer Code: " + getCustomerReferenceId());
    }

    @And("CreateSalesOrder user id api called")
    public void createsalesorderUserIdApiCalled() {
        setCreateSalesOrderResponse(NeuconnectFunction.CreateSalesOrder(getJwtToken(),getCustomerReferenceId(),getWhsCode(),getItemCode(),getQuantity(),getUoM(),getBarCode(),getBinCode(),getDeliveryDate()));
        PrintUtil.PrintSuccessLog(getCreateSalesOrderResponse());


    }

    @And("CreateSalesOrder  list api")
    public void createsalesorderListApi() {
        setListAllSalesOrderResponse(NeuconnectFunction.ListAllSalesOrder(getAdminToken()));
        PrintUtil.PrintSuccessLog(getListAllSalesOrderResponse());
    }

    @And("isApiHandled is verified as {string} to test CreateSalesOrder")
    public void isapihandledIsVerifiedAsToTestCreateSalesOrder(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getCreateSalesOrderResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list CreateSalesOrder")
    public void isrequestsuccessIsVerifiedAsToTestListCreateSalesOrder(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getCreateSalesOrderResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list CreateSalesOrder")
    public void statuscodeIsVerifiedAsStatusCodeToTestListCreateSalesOrder(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getCreateSalesOrderResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list CreateSalesOrder")
    public void messageIsVerifiedToTestListCreateSalesOrder(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getCreateSalesOrderResponse(),scenario.getName());
    }
}
