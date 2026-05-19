package ApiAutomation.Neuconnect.POM.Auth.Authentication;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ListAllOTPs {
    public static String listAllOtps(String bearerToken) {
        try {
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + bearerToken)  // Add Bearer token in the header
                    .when()
                    .get(AuthEndpoints.ListOTPs)  // Assuming ListOTPs is the endpoint for listing all OTPs
                    .then()
                    //.statusCode(statusCode)
                    .extract()
                    .response();

            return response.getBody().asString();
        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }
}
