package ApiAutomation.Neuconnect.POM.Auth.Notification;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class AddNotification {

    public static String addNotification(String jwtToken, String title, String message, String description, String at, String targetNamespace, String sendToUserType, String tag, String userId) {
        try {
            // Create the JSON payload
            JSONObject payload = new JSONObject()
                    .put("title", title)
                    .put("message", message)
                    .put("description", description)
                    .put("at", at)
                    .put("targetNamespace", targetNamespace)
                    .put("sendToUserType", sendToUserType)
                    .put("tag", tag)
                    .put("userId", userId);

            // Print the payload for debugging
//            System.out.println("PAYLOAD: " + payload.toString());

            // Send the POST request with JWT Token
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + jwtToken) // Add JWT token to Authorization header
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.AddNotification)
                    .then()
                    .extract()
                    .response();

            // Return the response body as a string
            return response.getBody().asString();
        } catch (Exception err) {
            // Log and return any errors
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

}
