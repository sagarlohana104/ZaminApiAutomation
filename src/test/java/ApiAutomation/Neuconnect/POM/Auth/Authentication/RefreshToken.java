package ApiAutomation.Neuconnect.POM.Auth.Authentication;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class RefreshToken
{
    public static String refreshToken (String accessToken , String refreshToken ){
        try{
            JSONObject payload = new JSONObject()
                    .put("accessToken",accessToken)
                    .put("refreshToken",refreshToken);

            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.RefreshToken)

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
}
