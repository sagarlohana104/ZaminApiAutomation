package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Asserts.AssertUtils;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.security.SecureRandom;

public class CreatedirectITSteps {
    public String getUserToken() {
        return UserToken;
    }

    public void setUserToken(String userToken) {
        UserToken = userToken;
    }

    public String getAdminToken() {
        return AdminToken;
    }

    public void setAdminToken(String adminToken) {
        AdminToken = adminToken;
    }

    private String UserToken;

    public String getItrId() {
        return ItrId;
    }

    public void setItrId(String itrId) {
        ItrId = itrId;
    }

    private String ItrId;
    private String AdminToken;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public String getCreateITResponse() {
        return CreateITResponse;
    }

    public void setCreateITResponse(String createITResponse) {
        CreateITResponse = createITResponse;
    }

    private Scenario scenario;
    @Before
    public void BeforeScenario(Scenario scenario){
        this.scenario=scenario;
    }
    private String CreateITResponse;
    private int quantity;
    private String barCode;
    private String binCode;
    private String  series;
    private String  seriesName;

    public String getListDirectITResponse() {
        return ListDirectITResponse;
    }

    public void setListDirectITResponse(String listDirectITResponse) {
        ListDirectITResponse = listDirectITResponse;
    }

    private String ListDirectITResponse;


   @Given("User login for create IT")
    public void UserloginforcreateIT(){
       setUserToken(AuthGenerics.getJwtToken(Login.login(Credentials.userEmail,Credentials.userPass)));
       PrintUtil.PrintSuccessLog(getUserToken());
   }

    @And("Admin login for create IT")
    public void adminLoginForCreateIT() {
       setAdminToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
       PrintUtil.PrintSuccessLog(getAdminToken());
    }

    @And("Extract itr id from itr list")
    public void extractItrIdFromItrList() {
       setItrId(GenericExtractorsValidators.getLastDetail(
               NeuconnectFunction.ListAllITRS(getAdminToken()),"data.data","id","2"
       ));
       PrintUtil.PrintSuccessLog("Itr id extract from list " + getItrId());
    }

    @And("Set payload for create IT")
    public void setPayloadForCreateIT() {

        setQuantity(1);

            setBarCode("String");

            setBinCode("String");

            setSeries("200");

            setSeriesName("IT2026");
        }

    @And("Create IT api called")
    public void createITApiCalled() {
       setCreateITResponse(NeuconnectFunction.CreateIT(getUserToken(),getItrId(),getQuantity(),getBarCode(),getBinCode(),getSeries(),getSeriesName()));
        PrintUtil.PrintSuccessLog("RESPONSE: " + getCreateITResponse());

    }

    @And("isApiHandled is verified as {string} to test CreateInventoryTransfer")
    public void isapihandledIsVerifiedAsToTestCreateInventoryTransfer(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getCreateITResponse(),scenario.getName());
    }

    @And("isRequestSuccess is verified as {string} to test list CreateInventoryTransfer")
    public void isrequestsuccessIsVerifiedAsToTestListCreateInventoryTransfer(String arg0) {
       AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getCreateITResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list CreateInventoryTransfer")
    public void statuscodeIsVerifiedAsStatusCodeToTestListCreateInventoryTransfer(int arg0) {
       AssertUtils.verifiesToReceiveStatusCode(arg0,getCreateITResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list CreateInventoryTransfer")
    public void messageIsVerifiedToTestListCreateInventoryTransfer(String arg0) {
       AssertUtils.verifiesToReceiveMessage(arg0,getCreateITResponse(),scenario.getName());
    }

    @And("List Direct it api called")
    public void listDirectItApiCalled() {
       setListDirectITResponse(NeuconnectFunction.ListAllInventoryTransfers(getAdminToken()));
       PrintUtil.PrintSuccessLog(getListDirectITResponse());

    }
}

