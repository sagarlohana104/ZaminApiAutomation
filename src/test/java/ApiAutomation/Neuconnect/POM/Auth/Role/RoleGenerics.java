package ApiAutomation.Neuconnect.POM.Auth.Role;

import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoleGenerics
{

    public static String getLastRoleDetails (String ListAllRolesResponse , String arg , String index){
        try{
            JsonPath jsonPath = new JsonPath(ListAllRolesResponse);
            return jsonPath.getString("data["+index+"]." + arg);
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static Boolean findRoleFromRolesList (String response , String roleName , String param){
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString(param);  // Fetch the role name
//                System.out.println(roleObject.getString("roleName"));
                if (roleNameFromList.equalsIgnoreCase(roleName)) {
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


    public static String getRoleDetailFromRoleName (String response , String roleName , String param){
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
    public static String getIdByRoleName(String response, String toSearchValue) {
        try {
            // Convert the response to JSONObject
            JSONObject jsonResponse = new JSONObject(response);

            // Get the data array from the response
            JSONArray data = jsonResponse.getJSONArray("data");

            // Loop through each object in the data array
            for (int i = 0; i < data.length(); i++) {
                JSONObject role = data.getJSONObject(i);

                // Check if the role name matches the search value
                if (role.getString("name").equalsIgnoreCase(toSearchValue)) {
                    return role.getString("id");  // Return the role ID
                }
            }

            // If no match is found, return null
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            PrintUtil.PrintErrorLog(e.getMessage());
            return null;
        }
    }



}
