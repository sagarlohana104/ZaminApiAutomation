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

    /*  @Test
      public void AddCategoryTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          RestAssured.port = 31124;
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.AddCategory(token, "Categry",  true)
          );
      }

      @Test
      public void AddCompanyTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          RestAssured.port = 31124;
          String Response = RFIDFunctions.AddCompany(token, "System1", "USA1", "New York1", "Defence1", "+92303028722", true);
          PrintUtil.PrintSuccessLog("Response: " + Response);
      }

      @Test
      public void AddDepartmentTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.AddDepartment(token, "Data", "MHkuRMSJfv", true)
          );
      }

      @Test
      public void AddLocationTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.Addlocation(token, "vVCOSl1don", "Hyderabad", "+9230833002", "Defence", "Autoban", "4th", "clifton blick 8", "pakistan", "sindh", "hyd", true, null)
          );
      }

      @Test
      public void AddCostCenterTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.AddCostCenter(token, "Furniture", "vVCOSl1don", "Table", "NVVPBydnMI", "eJhTvZtxx9", "good", true)
          );
      }

      @Test
      public void AddSubCategoryTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.AddsubCategory(token, "Glass", "aXQe3eq0er",  true)
          );
      }

      @Test
      public void AddAssetTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.AddAsset(token, "C-1401-00000000000008", "Executive Chairs", "jab1h1yb12", "aXQe3eq0er", "iWLHkUmkt4", "X6jgob7cEC", "ZusrCc77rP", "at1g4xNkte", "Align with doc", true)
          );

      }

      @Test
      public void updateCategoryTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.updateCategory(token, "5KWzgdTyg4", "Black Board", true)

          );
      }

      @Test
      public void updateCompanyTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.updateCompany(token, "MHkuRMSJfv", "Folio", "Pakistan1", "Karachi1", "tariqroad1", "+923098766", true)
          );
      }

      @Test
      public void updateDepartmentTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.updateDepartment(token, "SQL", "gjNmHfKNtB", "MHkuRMSJfv", true)
          );
      }

      @Test
      public void UpdateLocationTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          List<String> departmentIds = new ArrayList<>();
          departmentIds.add("eJhTvZtxx9");
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.updateLocation(token, "NVVPBydnMI", "vVCOSl1don", "dubai", "+92309330467", "karimabad", "pakistan", "kpk", "gilgit", "Defence", "bussiness", "4th", departmentIds, true)
          );
      }

      @Test
      public void UpdateCostCenterTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.updateCostCenter(token, "0jbdxWbqbz", "Finance", "bKMF7rILlF", "Operational", "eUzpLPJdHJ", "0nY1l4DiCz", "good", true)
          );
      }

      @Test
      public void updateSubCategoryTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.updatesubCategory(token, "at1g4xNkte", "Mobile", "AtYaJbiiJ3", true)
          );
      }

      @Test
      public void removeCategoryTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.removeCategoryById(token, "MHl64tGj7v")
          );
      }

      @Test
      public void removeCompanyByIdTest() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.removeCompanyById(token, "0uWhG8Wmla")
          );
      }

      @Test
      public void removeDepartmentById() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.removeDepartmentById(token, "zpEOOFWQu4")
          );
      }

      @Test
      public void removeLocationByIdTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.removeLocationById(token,"NVVPBydnMI")
          );
      }

      @Test
      public void removeCostCenterById(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.removeCostCenterById(token,"vP2izFYBpa")
          );
      }

      @Test
      public void removeSubCategoryById()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.removesubCategoryById(token,"at1g4xNkte")
          );
      }

      @Test
      public void searchCategoryTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.searchCategory(token,"Table")
          );
      }

      @Test
      public void searchCompanyTest(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.searchCompany(token,"QBs","pakistan","karachi")
          );
      }

      @Test
      public void searchDepartment(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.searchDepartment(token,"9USjxW5mKO","science")
          );
      }

      @Test
      public void searchLocationTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.searchLocation(token, "9USjxW5mKO","pakistan","clifton","karachi","sindh","defence")
          );
      }


      @Test
      public void searchCostCenterTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.searchCostCenter(token,"0jbdxWbqbz","Finance","bKMF7rILlF","Operational","eUzpLPJdHJ")
          );
      }

      @Test
      public void searchSubCategoryTest(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.searchSubCategory(token,"Glass","aXQe3eq0er")
          );
      }

      @Test
      public void GetCategoryByIdTest(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.GetCategoryById(token,"cwWW66DVXK")
          );
      }


      @Test
      public void GetCompanyByIdTest(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.GetCompanyById(token,"xKH9nXFjFa")
          );
      }

      @Test
      public void GetLocationByIdTest(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.GetLocationById(token,"LOC-35910")
          );

      }



      @Test
      public void GetDepartmentByIdTest(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.GetDepartmentById(token,"iWLHkUmkt4")
          );
      }

      //GetLocationById is an pending

      @Test
      public void GetCostCenterById()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.GetCostCenterById(token, "hVKDwvK56E")
          );
      }



      @Test
      public void GetsubCategoryByIdTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));

          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.GetSubCategoryrById(token, "rGeAB7DY6m")
          );
      }

      @Test
      public void ListCategoryTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.ListCategory(token)
          );
      }



      @Test
      public void ListCompanyTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.ListCompany(token)
          );
      }



      @Test
      public void ListDepartmentTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.ListDepartment(token)
          );
      }
      @Test
      public void ListLocationTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.ListLocation(token)
          );
      }@Test
      public void ListCostCenterTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.ListCostCenter(token)
          );

      }
      @Test
      public void ListSubCategoryTest()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.ListSubCategory(token)
          );
      }

      @Test
      public void testTotalListCountInRFIDService() {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
  //        PrintUtil.PrintSuccessLog("Token: " + token);

          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          System.out.println("TOTAL COUNT : "+ NeuconnectGenerics.listCountOfAllApis(token));



  //        int totalCount = RFIDFunctions.getTotalListCount(token);
  //        PrintUtil.PrintSuccessLog("Total List Count in RFID Service: " + totalCount);
      }

      @Test
      public void GetDepartmentByCompanyId()
      {
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.GetDepartmentByCompanyId(token)
          );
      }

      @Test
      public void removeAllCompany (){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          for (int i=0; i<100;i++){
              PrintUtil.PrintSuccessLog(
                      RFIDFunctions.removeCompanyById(token, GenericExtractorsValidators.getLastDetail(RFIDFunctions.ListCompany(token),"data.company","companyId","0"))
              );
          }
      }
      @Test
      public void removeAllDepartment(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail, Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          for (int i=0;i<100;i++){
              PrintUtil.PrintSuccessLog(
                      RFIDFunctions.removeDepartmentById(token,GenericExtractorsValidators.getLastDetail(RFIDFunctions.ListDepartment(token),"data.departments","departmentId","0"))
              );
          }
      }
      @Test
      public void removeAllLocation(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          for (int i=0;i<1000;i++){
              RFIDFunctions.removeLocationById(token,GenericExtractorsValidators.getLastDetail(RFIDFunctions.ListLocation(token),"data.locations","locationId","0"));
          }
      }


      @Test
      public void removeAllCategory(){
          String token = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          for (int i=0;i<1000;i++){
              RFIDFunctions.removeCategoryById(token,GenericExtractorsValidators.getLastDetail(RFIDFunctions.ListCategory(token),"data.categories","categoryId","0"));
          }
      }

      @Test
      public void removeAllSubcategory(){
          String token=AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          for (int i=0;i<1000;i++){
              RFIDFunctions.removesubCategoryById(token,GenericExtractorsValidators.getLastDetail(RFIDFunctions.ListSubCategory(token),"data.subCategories","subCategoryId","0"));
          }
      }

      @Test
      public void addUserTest(){
          String token=AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.addUser(token,"sheerazshaikh12","sheerazshaikh81@gmail.com","+92 336 3044169","COM-96936",null,"Administrator",true,null)
          );
      }

      @Test
      public void removeUser(){
          String token=AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
          PrintUtil.PrintSuccessLog(token);
          PortUtils.setPort(envConfig.getEnvInteger("RFID_PORT"));
          PrintUtil.PrintSuccessLog(
                  RFIDFunctions.ListUserTest(token)

          );
      }*/
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



}
