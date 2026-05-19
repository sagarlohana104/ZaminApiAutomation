package ApiAutomation.Neuconnect.POM.Auth.UserManagement;


import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserGenerics
{
    public static String getUserDetailFromUserName (String response , String userName , String param){
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString("name");  // Fetch the user name
                if (roleNameFromList.equalsIgnoreCase(userName)) {
                    return roleObject.getString(param);
                }
            }
            return "User Name not present ";
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.getMessage();
        }
    }

    public static String getLastUserDetails (String ListAllUsersResponse , String arg , String index){
        try{
            JsonPath jsonPath = new JsonPath(ListAllUsersResponse);
            return jsonPath.getString("data["+index+"]." + arg);
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static Boolean findUserFromUsersList (String response , String userName , String param){
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString(param);  // Fetch the role name
//                System.out.println(roleObject.getString("roleName"));
                if (roleNameFromList.equalsIgnoreCase(userName)) {
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
    public static String findUserIdFromUsersList(String response, String userName) {
        try {
            // Parse the response as a JSON object
            JSONObject jsonObject = new JSONObject(response);

            // Extract the data array from the JSON object
            JSONArray dataArray = jsonObject.getJSONArray("data");

            // Iterate through each object in the data array
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject userObject = dataArray.getJSONObject(i);

                // Fetch the username from the object using "name"
                String nameFromList = userObject.getString("name");

                // Compare it with the given username
                if (nameFromList.equalsIgnoreCase(userName)) {
                    // If found, return the ID
                    return userObject.getString("userId");
                }
            }

            // Return null if username not found
            return null;
        } catch (Exception err) {
            // Print error message and return null in case of exception
            PrintUtil.PrintErrorLog(err.getMessage());
            return null;
        }
    }

}
