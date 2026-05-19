package ApiAutomation.Neuconnect.POM.Auth.Authentication;


import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

public class AuthGenerics
{

    public static String getJwtToken (String response){
        try{
            JsonPath jsonPathEvaluator = new JsonPath(response);
            return jsonPathEvaluator.getString("data.token");
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }

    public static String getRefreshToken (String response){
        JsonPath jsonPathEvaluator = new JsonPath(response);
        return jsonPathEvaluator.getString("data.refreshToken");
    }

    public static Boolean findDetailFromAuthResponse(String response, String roleName, String dataParam, String param) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray dataArray = dataObject.getJSONArray(dataParam);
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString(param);  // Fetch the role name
                if (roleNameFromList.equalsIgnoreCase(roleName)) {
                    return true;  // Role found, return true
                }
            }
            return false;  // Role not found, return false
        } catch (Exception err) {
            PrintUtil.PrintErrorLog(err.getMessage());
            return false;  // Return false in case of any exception
        }
    }
}
