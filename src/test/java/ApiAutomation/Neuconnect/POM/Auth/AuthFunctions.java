package ApiAutomation.Neuconnect.POM.Auth;

import ApiAutomation.Neuconnect.BasePage;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.UnknownHostException;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCause;

public class AuthFunctions extends BasePage
{

    public static String ForgetPassword(String email, String deviceId){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("deviceId",deviceId );
            return MakeApiCall(payload.toString(),AuthEndpoints.ForgetPassword,Constants.EMPTY_PAYLOAD ,Constants.POST,false,"","");
        }
        catch (Exception e){
            Throwable rootCause = getRootCause(e);

            if (rootCause instanceof ConnectException) {
                System.out.println("❌ Connection refused: " + rootCause.getMessage());
            } else if (rootCause instanceof UnknownHostException) {
                System.out.println("❌ Unknown host: " + rootCause.getMessage());
            } else if (rootCause instanceof org.apache.http.conn.ConnectTimeoutException) {
                System.out.println("❌ Connection timed out: " + rootCause.getMessage());
            } else {
                System.out.println("❌ General exception: " + e.getMessage());
                e.printStackTrace();
            }
            return e.toString();
        }
    }

    public static String VerifyOTP(String email, String otp){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("otp",otp );

            Response resp=RestAssured
                    .given()
                        .contentType("application/json")
                        .accept("application/json")
                        .header("DeviceId","string")
                        .body(payload.toString())
                    .when()
                        .post(AuthEndpoints.VerifyOtp)
                    .then()
                        .extract()
                        .response();
//            PrintUtil.PrintSuccessLog("RESPONSE : "+ resp.getBody().asString());
            return resp.getBody().asString();
//            return MakeApiCall(payload.toString(),AuthEndpoints.VerifyOtp,Constants.EMPTY_PAYLOAD ,Constants.POST,false,"","");
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }

    public static String CreateNewPasswordAfterOTP(String email, String password){
        try{
            JSONObject payload = new JSONObject()
                    .put("email",email)
                    .put("password",password );
            return MakeApiCall(payload.toString(),AuthEndpoints.CreateNewPasswordAfterOtp,Constants.EMPTY_PAYLOAD ,Constants.POST,false,"","");
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }
}
