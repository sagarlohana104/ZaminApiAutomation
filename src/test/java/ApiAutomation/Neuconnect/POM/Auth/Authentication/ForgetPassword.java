package ApiAutomation.Neuconnect.POM.Auth.Authentication;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class ForgetPassword
{
    public static String forgetPassword (String email , int statusCode ){
        try{

            JSONObject payload = new JSONObject()
                    .put("email",email);

            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .body(payload.toString())
                    .when()

                        .post(AuthEndpoints.ForgetPassword)
                    .then()
                        .statusCode(statusCode)
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
