package ApiAutomation.Neuconnect.POM.Auth.Authentication;


import ApiAutomation.Neuconnect.POM.Auth.AuthEndpoints;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class ChangePassword
{
    public static String changePassword (String email , String currentPassword , String newPassword ){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("currentPassword",currentPassword)
                    .put("newPassword",newPassword);
            System.out.println("PAYLOAD : "+payload.toString());
            Response response = RestAssured
                    .given()
                        .contentType("application/json")
                        .body(payload.toString())
                    .when()

                    .post(AuthEndpoints.ChangePassword)
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
