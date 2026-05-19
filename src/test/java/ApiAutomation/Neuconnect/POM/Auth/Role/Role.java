package ApiAutomation.Neuconnect.POM.Auth.Role;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;

public class Role
{

    public static String createRole (String roleName , String bearerToken,String description,String tag ){
        try{
            JSONObject payload = new JSONObject()
                    .put("roleName",roleName)
                    .put("description",description)
                    .put("tag",tag);

            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.CreateRole)

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

    public static String deleteRole (String roleId , String bearerToken){
        try{

            JSONObject payload = new JSONObject()
                    .put("roleId",roleId);
            System.out.println(payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .patch(AuthEndpoints.DeleteRole)
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

    public static String listRoles (String bearerToken){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.ListRoles+"?LastCount=1000&skipRecord=0")
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



    public static String updateRole (String roleId , String roleName , String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("roleId",roleId)
                    .put("roleName",roleName);
            System.out.println(payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .put(AuthEndpoints.UpdateRole)
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


    public static String getRoleById (String roleId , String bearerToken ){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.GetRole +"/{id}" , roleId)
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

    public static String getPoliciesByRoleId (String roleId , String bearerToken){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.GetPoliciesByRoleId +"/{id}" , roleId)
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

    public static String addRoleToUser (String userId , String roleId, String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("userId",userId)
                    .put("roleId",roleId);
            System.out.println("Add Role To User Payload : "+payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.AddRoleToUser)
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
    public static String addActionInRoles(String actionName, List<String> roleIds, String bearerToken) {
        try {
            // Create the payload using JSONObject
            JSONObject payload = new JSONObject()
                    .put("name", actionName)
                    .put("roleIds", roleIds);

            // Make the POST request using RestAssured
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + bearerToken)
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.AddActionInRoles)  // URL endpoint for adding action in roles
                    .then()
                    .extract()
                    .response();

            // Return the response as a string
            return response.getBody().asString();
        } catch (Exception err) {
            // Print error and return error string
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }
    public static String addActionsInRole(String roleId, List<String> actions, String bearerToken) {
        try {
            // Create the JSON payload using JSONObject
            JSONObject payload = new JSONObject()
                    .put("roleId", roleId)
                    .put("actions", actions);
            PrintUtil.PrintSuccessLog(payload.toString());
            // Make the POST request using RestAssured
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + bearerToken)
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.AddActionsInRole) // Endpoint for adding actions in role
                    .then()
                    .extract()
                    .response();

            // Return the response body as a string
            return response.getBody().asString();
        } catch (Exception err) {
            // Print and return the error message
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }


}
