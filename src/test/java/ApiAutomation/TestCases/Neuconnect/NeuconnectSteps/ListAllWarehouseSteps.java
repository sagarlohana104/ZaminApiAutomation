package ApiAutomation.TestCases.Neuconnect.NeuconnectSteps;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
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

public class ListAllWarehouseSteps {
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;

    public String getListAllWarehouseResponse() {
        return ListAllWarehouseResponse;
    }

    public void setListAllWarehouseResponse(String listAllWarehouseResponse) {
        ListAllWarehouseResponse = listAllWarehouseResponse;
    }

    private String ListAllWarehouseResponse;
    private Scenario scenario;

    @Before
    public void beforescenario(Scenario scenario){
        this.scenario=scenario;
    }


    @Given("A user has logged in access with list all warehouse")
    public void aUserHasLoggedInAccessWithListAllWarehouse() {
        setJwtToken(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
        PrintUtil.PrintSuccessLog(getJwtToken());
    }

    @When("List all warehouses api called")
    public void listAllWarehousesApiCalled() {
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        setListAllWarehouseResponse(NeuconnectFunction.ListAllWarehouse(getJwtToken()));
        PrintUtil.PrintSuccessLog(getListAllWarehouseResponse());
    }

    @And("isApiHandled is verified as {string} to test warehouses")
    public void isapihandledIsVerifiedAsToTestWarehouses(String arg0) {
        AssertUtils.verifiesToReceiveIsApiHandled(arg0,getListAllWarehouseResponse(),scenario.getName());

    }

    @And("isRequestSuccess is verified as {string} to test warehouses")
    public void isrequestsuccessIsVerifiedAsToTestWarehouses(String arg0) {
        AssertUtils.verifiesToReceiveIsRequestSuccess(arg0,getListAllWarehouseResponse(),scenario.getName());
    }

    @And("statusCode is verified as {int} to test list warehouses")
    public void statuscodeIsVerifiedAsStatusCodeToTestListWarehouses(int arg0) {
        AssertUtils.verifiesToReceiveStatusCode(arg0,getListAllWarehouseResponse(),scenario.getName());
    }

    @And("message is verified {string} to test list warehouses")
    public void messageIsVerifiedToTestListWarehouses(String arg0) {
        AssertUtils.verifiesToReceiveMessage(arg0,getListAllWarehouseResponse(),scenario.getName());
    }
}
