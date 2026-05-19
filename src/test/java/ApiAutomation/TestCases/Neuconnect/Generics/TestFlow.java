package ApiAutomation.TestCases.Neuconnect.Generics;

import ApiAutomation.Neuconnect.POM.Auth.Actions.ActionGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Actions.Actions;
import ApiAutomation.Neuconnect.POM.Auth.AuthFunctions;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.POM.Auth.Role.Role;
import ApiAutomation.Neuconnect.POM.Auth.Role.RoleGenerics;
import ApiAutomation.Neuconnect.POM.Auth.UserManagement.User;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.Dumps.AuthActionDumps;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFlow {

    @BeforeAll
    public static void setup() {
//        PrintUtil.PrintErrorLog(envConfig.getEnv("BASE_URL")+":"+envConfig.getEnvInteger("PORT"));
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = envConfig.getEnv("BASE_URL");
        RestAssured.port = envConfig.getEnvInteger("PORT");
    }

    @Test
    public void CreateUserWithSpecificActionsServiceWise(){
        String Service="FranchiseApi";
        String RoleName="asaas";
        String userEmail = "FranchiseUser@gmail.com";
        String userPass  = "Ajeeks!23";
        String userType = Constants.FRANCHISE;

        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog("CREATE ROLE : "+Role.createRole(RoleName,jwtToken,RoleName,RoleName));
        String roleID= GenericExtractorsValidators.getDetailFromName(Role.listRoles(jwtToken),"roles","name",RoleName,"id");
        PrintUtil.PrintSuccessLog("ROLE ID " + roleID);
        if(roleID == null){
            Assertions.assertTrue(true, "ROLE ID IS NULL");
        }
        JSONArray serviceWiseActions=ActionGenerics.getActionsServiceWise(Actions.getOverallActions(jwtToken),Service);
        // Convert JSONArray to List<String>
        List<String> list = new ArrayList<>();
        for (int i = 0; i < serviceWiseActions.length(); i++) {
            list.add(serviceWiseActions.getString(i));
        }
        PrintUtil.PrintSuccessLog(Role.addActionsInRole(roleID,list,jwtToken));
        List<String> roleIds = Collections.singletonList(roleID);
        PrintUtil.PrintSuccessLog(User.addUser(userEmail,userEmail,userPass,userPass,"2025-01-23T18:35:03.609Z",roleIds,userType,jwtToken));
    }

    @Test
    public void AddingGetOverAllActionsInActionsServiceWise (){
        String Service="SLAAPI";
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        JSONArray serviceWiseActions=ActionGenerics.getActionsServiceWise(Actions.getOverallActions(jwtToken),Service);
//        PrintUtil.PrintLog(serviceWiseActions.getString(0) );
        for(int i=0 ; i < serviceWiseActions.length() ;i++){
            PrintUtil.PrintSuccessLog(Actions.addActions(serviceWiseActions.getString(i),"test",jwtToken));
        }
    }

    @Test
    public void createArole (){
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(Role.createRole("SHSH",jwtToken,"",""));
    }


    @Test
    public void AddingGetOverAllActionsInACreatedRoleServiceWise (){
        String Service="Messenger";
        String RoleName="Messenger";
        String jwtToken = AuthGenerics.getJwtToken(Login.login("ageek@ageek.com","Ageeks!23"));
        Role.createRole(RoleName,jwtToken,"","");
        String roleID= RoleGenerics.getIdByRoleName(Role.listRoles(jwtToken),RoleName);
        PrintUtil.PrintSuccessLog("ROLE ID " + roleID);
        JSONArray serviceWiseActions=ActionGenerics.getActionsServiceWise(Actions.getOverallActions(jwtToken),Service);
        // Convert JSONArray to List<String>
        List<String> list = new ArrayList<>();
        for (int i = 0; i < serviceWiseActions.length(); i++) {
            list.add(serviceWiseActions.getString(i));
        }
        PrintUtil.PrintSuccessLog(Role.addActionsInRole(roleID,list,jwtToken));

    }

    @Test
    public void createUser (){
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        String roleID= RoleGenerics.getIdByRoleName(Role.listRoles(jwtToken),"Franchise");
        PrintUtil.PrintSuccessLog("ROLE ID " + roleID);
        List<String> roleIds = Collections.singletonList(roleID);
        PrintUtil.PrintSuccessLog(User.addUser("FranchiseUser@gmail.com","SLA","Ageeks!23","Ageeks!23","2023-03-03",roleIds,"Franchise",jwtToken));
    }

    @Test
    public void AssignGetOverAllActionsInAPreDefinedRole (){
        String Service="ProductApi";
        String RoleName="CRMPartner";
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        String roleID= RoleGenerics.getIdByRoleName(Role.listRoles(jwtToken),RoleName);
        PrintUtil.PrintSuccessLog("ROLE ID " + roleID);
        JSONArray serviceWiseActions=ActionGenerics.getActionsServiceWise(Actions.getOverallActions(jwtToken),Service);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < serviceWiseActions.length(); i++) {
            list.add(serviceWiseActions.getString(i));
        }
        PrintUtil.PrintSuccessLog(Role.addActionsInRole(roleID,list,jwtToken));
    }

    @Test
    public void getOverallActions (){
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(Actions.getOverallActions(jwtToken));
    }

    @Test
    public void getAllRoles (){
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        PrintUtil.PrintSuccessLog(Role.listRoles(jwtToken));
    }


    @Test
    public void TESTBrandController(){
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
//        PrintUtil.PrintSuccessLog(Functions.AddProductCategory(jwtToken,"2345"));
//        PrintUtil.PrintSuccessLog(Functions.GetBrandByID(jwtToken,"2cdff3ef-27b6-4564-a414-a1f33ecf40e5"));
    }

    @Test
    public void GetJwtToken (){
        PrintUtil.PrintSuccessLog(AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass)));
    }


    @Test
    public void assignPreDefinedActionsToAPreDefinedRole (){
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
        String roleName = "CRMPartner";    // predefined role
        String roleID= GenericExtractorsValidators.getDetailFromName(Role.listRoles(jwtToken),"roles","name",roleName,"id");
        PrintUtil.PrintSuccessLog("ROLE ID : "+roleID);
        PrintUtil.PrintSuccessLog(Role.addActionsInRole(roleID, AuthActionDumps.ListCspActions(),jwtToken));
    }

    @Test
    public void testverifyotp (){
        PrintUtil.PrintSuccessLog(
                AuthFunctions.VerifyOTP("sqa.franchise4@yopmail.com","9455")
        );
    }

    @Test
    public void forgetPassword (){
        PrintUtil.PrintSuccessLog(
                AuthFunctions.ForgetPassword("test.partner3@yopmail.com","")
        );
    }


    // imp so whenever database is refreshed , our ci/cd couldn't get interrupted
    @Test
    public void CreateUsersFromCredentialsFile (){

    }


}
