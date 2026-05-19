package ApiAutomation.Neuconnect.POM.Auth.UserManagement;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;

public class User
{

    public static String addUser (String email , String name , String password , String confirmedPassword , String expiryDate , List<String> roleIds , String userType, String bearerToken)
    {
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("name",name)
                    .put("password",password)
                    .put("confirmedPassword",confirmedPassword)
                    .put("isRefreshTokenRevokable",true)
                    .put("expiryDate",expiryDate)
                    .put("roleIds",roleIds)
                    .put("userType",userType);
            System.out.println("ADD USER PAYLOAD : "+payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.AddUser)
                    .then()
//                        .statusCode(200)
                        .extract()
                        .response();

            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String deleteUser (String userId , String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("userId",userId);
//            System.out.println("DELETE USER"+ payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .patch(AuthEndpoints.DeleteUser)
                    .then()
//                        .statusCode(200)
                        .extract()
                        .response();

            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }


    public static String getUser (String id , String bearerToken){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.GetUser+"/{id}" ,id)
                    .then()
//                        .statusCode(200)
                        .extract()
                        .response();
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String getUserDetailsWithActions (String id , String bearerToken){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.GetUserDetailsWithActions+"/{id}" ,id)
                    .then()
//                        .statusCode(200)
                        .extract()
                        .response();
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String ListUsers (String bearerToken){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.ListUsers+"?LastCount=1000&skipRecord=0")
                    .then()
//                        .statusCode(200)
                        .extract()
                        .response();
            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String updateUser (String userId , String email , String name , String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("userId",userId)
                    .put("email",email)
                    .put("name",name);
//            System.out.println("Update User Payload : "+payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.UpdateUser)
                    .then()
//                        .statusCode(200)
                        .extract()
                        .response();

            return response.getBody().asString();
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }
}
