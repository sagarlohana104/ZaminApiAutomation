package ApiAutomation.Neuconnect.POM.Auth.Authentication;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class GenerateOtp {
    public static String generateOtp(String email, String deviceId) {
        try {
            JSONObject payload = new JSONObject()
                    .put("email", email);

            Response response = RestAssured
                    .given()
                    .header("DeviceId", deviceId)
                    .contentType("application/json")
                    .body(payload.toString())
                    .when()
                    .post(AuthEndpoints.GenerateOTP)
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
