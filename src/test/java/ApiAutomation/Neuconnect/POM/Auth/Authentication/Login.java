package ApiAutomation.Neuconnect.POM.Auth.Authentication;

import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;


public class Login
{
    public static String login (String email , String password){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("password",password);
//            System.out.println(payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
//                        .accept("application/json")
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.Login)
                    .then()
//                        .statusCode(statusCode)
                        .extract()
                        .response();
//            System.out.print(response.getBody().asString());
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String loginViewer (String email , String password){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("password",password);
//            System.out.println("VIEWER PAYLOAD : "+payload.toString());
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
//                        .accept("application/json")
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.LoginViwer)
                    .then()
//                        .statusCode(statusCode)
                    .extract()
                    .response();
//            System.out.print(response.getBody().asString());
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String loginSLA (String email , String password){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("password",password);
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
//                        .accept("application/json")
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.LoginSLA)
                    .then()
//                        .statusCode(statusCode)
                    .extract()
                    .response();
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String loginByIqama (String iqamaNumber , String password){
        try{
            JSONObject payload = new JSONObject()
                    .put("iqamaId",iqamaNumber)
                    .put("password",password);
//            System.out.println(payload.toString());
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.LoginByIqama)
                    .then()
//                        .statusCode(statusCode)
                    .extract()
                    .response();
//            System.out.print(response.getBody().asString());
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String loginFranchise (String email , String password){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("password",password);
//            System.out.println(payload.toString());
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
//                        .accept("application/json")
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.LoginFranchise)
                    .then()
//                        .statusCode(statusCode)
                    .extract()
                    .response();
//            System.out.print(response.getBody().asString());
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String loginTechnician (String email , String password){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("password",password);
//            System.out.println(payload.toString());
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
//                        .accept("application/json")
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.LoginTechnician)
                    .then()
//                        .statusCode(statusCode)
                    .extract()
                    .response();
//            System.out.print(response.getBody().asString());
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }
}
