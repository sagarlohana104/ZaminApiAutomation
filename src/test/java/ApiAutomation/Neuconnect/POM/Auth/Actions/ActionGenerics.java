package ApiAutomation.Neuconnect.POM.Auth.Actions;


import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

public class ActionGenerics
{
    public static String getActionDetailFromActionName (String response , String roleName , String param){
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString("name");  // Fetch the role name
                if (roleNameFromList.equalsIgnoreCase(roleName)) {
                    return roleObject.getString(param);
                }
            }
            return "Role Name not present ";
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.getMessage();
        }
    }


    public static String getLastActionDetails (String ListAllRolesResponse , String arg , String index){
        try{
            JsonPath jsonPath = new JsonPath(ListAllRolesResponse);
            return jsonPath.getString("data["+index+"]." + arg);
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static Boolean findActionFromActionsList (String response , String actionName , String param){
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString(param);  // Fetch the role name
//                System.out.println(roleObject.getString("roleName"));
                if (roleNameFromList.equalsIgnoreCase(actionName)) {
//                    System.out.println("jefhbsibduibsdfuiuisdfb");
                    return true;
                }
            }
            return false;
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return false;
        }
    }

    public static JSONArray getActionsServiceWise ( String getOverAllActions , String service){
        try{
            JSONObject JSONOBJ = new JSONObject(getOverAllActions);
            JSONArray apisArray = JSONOBJ.getJSONArray("data");
            for (int i = 0; i < apisArray.length(); i++) {
                JSONObject api = apisArray.getJSONObject(i);
                if (api.getString("apiName").equalsIgnoreCase(service)) {
                    // Get the "routes" array
                    JSONArray routes = api.getJSONArray("routes");
                    return routes;
                }
            }
            return null;
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return null;
        }
    }
}
