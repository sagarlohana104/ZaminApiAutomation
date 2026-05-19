package ApiAutomation.Neuconnect.POM.Auth.Actions;



import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class Actions
{

    public static String addActions (String name , String tag , String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("name",name)
                    .put("tag",tag);
            System.out.println("Add Action Payload : "+ payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.AddActions)
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

    public static String addActionsInRole (String roleId , List<String> actions , String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("roleId",roleId)
                    .put("actions",actions);

            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.AddActionsInRole)
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

    public static String appendActionTag (String actionId , String tagToAppend , String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("actionId",actionId)
                    .put("tagToAppend" ,tagToAppend);

            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.AppendActionTag)
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

    public static String deleteAction(String actionId, String bearerToken) {
        try {
            // Wrap the single actionId string into a JSON Array
            JSONArray payload = new JSONArray();
            payload.put(actionId); // Adding the single string into the JSON array

            Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + bearerToken)
                    .body(payload.toString()) // Convert JSON Array to string
                    .when()
                    .put(AuthEndpoints.DeleteActions)
                    .then()
                    .extract()
                    .response();

            return response.getBody().asString();
        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }


    public static String deleteActions (List<String> actions , String bearerToken){
        try{
            JSONObject payload = new JSONObject()
                    .put("",actions);
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .put(AuthEndpoints.DeleteActions)
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

    public static String getAllAction (String bearerToken){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.GetAllAction)
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

    public static String getOverallActions (String bearerToken){
        try{
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                    .when()
                        .get(AuthEndpoints.GetOverAllActions)
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

    public static String removeActionTag (String actionId , String tagToRemove , String bearerToken)
    {
        try{
            JSONObject payload = new JSONObject()
                    .put("actionId",actionId)
                    .put("tagToRemove",tagToRemove);

            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(payload.toString())
                    .when()
                        .patch(AuthEndpoints.RemoveActionTag)
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
