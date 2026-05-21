package ApiAutomation.TestCases.Neuconnect.Generics;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;

import ApiAutomation.Neuconnect.POM.Neuconnect.Neuconnect.NeuconnectFunction;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.PortUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NeuconnectTestflow {

    @BeforeAll
    public static void setup() {
//        PrintUtil.PrintErrorLog(envConfig.getEnv("BASE_URL")+":"+envConfig.getEnvInteger("PORT"));
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = envConfig.getEnv("BASE_URL");
        RestAssured.port = envConfig.getEnvInteger("PORT");
    }






    @Test
    public void Listusertest() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListUsers(token)
        );
    }

    @Test
    public void ListDepartment() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListDepartment(token)
        );
    }

    @Test
    public void AddDepartment() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.AddDepartment(token, "")
        );
    }

    @Test
    public void ListAllWarehouse() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListAllWarehouse(token)
        );
    }
    @Test
    public void AddWarehousetoUser() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.AddWarehousetoUser(token, "","","")
        );
    }
    @Test
    public void Listwarehousebyuserid() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListwarehousebyuserId(token,"")
        );
    }
    @Test
    public void ListAllITRS() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListAllITRS(token)
        );
    }
    @Test
    public void CreateInventoryTransferRequest() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.CreateInventoryTransferRequest(token,"","","",1,"","","","","")

        );
    }
    @Test
    public void ListAllItemsByUserId() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.listAllItemCodebyUserId(token,"")
        );
    }
    @Test
    public void GetDocumentSeries() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.GetDocumentSeries(token,1250000001)
        );
    }
    @Test
    public void CreateIT() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.CreateIT(token,"",12,"","","","")
        );
    }
    @Test
    public void ListDirectIT() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListAllInventoryTransfers(token)
        );
    }

    @Test
    public void TransferReceiveDirect() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.CreateTransferReceiveDirect(token,"","","",2,"","","","")
        );
    }
    @Test
    public void ListAllTransferReceive() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListAllTransferReceive(token)
        );
    }

    @Test
    public void CreateGoodsIssue() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.CreateGoodsIssue(token,"","",2,"","","","","")
        );
    }
    @Test
    public void ListAllGoodIssue() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListAllGoodIssue(token)
        );
    }
    @Test
    public void ListAllProductionOrders() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.ListAllProductionOrders(token)
        );
    }

   /* @Test
    public void CreateProductionOrderItr() {
        String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(token);
        PortUtils.setPort(envConfig.getEnvInteger("Zamin_PORT"));
        PrintUtil.PrintSuccessLog(
                NeuconnectFunction.CreateProductionOrderItr(token,"","","","","","")
        );
    }*/


}
