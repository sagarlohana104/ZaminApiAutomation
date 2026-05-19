package ApiAutomation.Neuconnect.Utils.Api;

import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenericExtractorsValidators
{
    public static String getLastDetail (String response,String dataParam , String arg , String index){
        try{
//            PrintUtil.PrintSuccessLog(response);
            JsonPath jsonPath = new JsonPath(response);
//            PrintUtil.PrintSuccessLog(jsonPath.toString());
            return jsonPath.getString(dataParam+"["+index+"]." + arg);
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }


    public static String getLastIndexDetail (String response,String dataParam , String arg , String index){
        try{
//            PrintUtil.PrintSuccessLog(response);
            JsonPath jsonPath = new JsonPath(response);
            int listSize = JsonPath.from(response).getList("lists").size();
            return jsonPath.getString(dataParam+"["+(listSize-1)+"]." + arg);
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String getIdFromResponse (String response, String arg){
        try{
            JsonPath jsonPath = new JsonPath(response);
            return jsonPath.getString(arg);        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String getDetailFromName(String response ,String dataParam, String nameParam , String nameValue , String returnParam){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray dataArray = dataObject.getJSONArray(dataParam);
//            PrintUtil.PrintSuccessLog(dataArray.toString());
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
//                PrintUtil.PrintSuccessLog("ROLE OBJECT "+i+":  "+roleObject.toString());
                String roleNameFromList = roleObject.getString(nameParam);  // Fetch the role name
//                PrintUtil.PrintSuccessLog("NAME :"+i +" "+roleNameFromList);
//                PrintUtil.PrintSuccessLog("VALUE: "+nameValue);
                if (roleNameFromList.equalsIgnoreCase(nameValue)) {
                    return roleObject.getString(returnParam);
                }
            }
            return "Name not present ";
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }

    public static String getDetailFromNameWithoutDataParam (String response , String nameParam , String nameValue , String returnParam){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("data");
//            PrintUtil.PrintSuccessLog(dataArray.toString());
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString(nameParam);  // Fetch the role name
                if (roleNameFromList.equalsIgnoreCase(nameValue)) {
                    return roleObject.getString(returnParam);
                }
            }
            return "Name not present ";
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }

    public static Boolean findProductFromProductList (String response , String name , String param){
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONArray dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString(param);  // Fetch the role name
                if (roleNameFromList.equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception err){
            System.out.println(err.toString());
            return false;
        }
    }

    public static Boolean findProductFromProductListWithDataParam (String response ,String dataParam, String name , String param){
        try{

            JSONObject jsonObject = new JSONObject(response);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray dataArray = dataObject.getJSONArray(dataParam);
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject roleObject = dataArray.getJSONObject(i);
                String roleNameFromList = roleObject.getString(param);  // Fetch the role name
//                PrintUtil.PrintSuccessLog(roleNameFromList  +"  -  "+name);
                if (roleNameFromList.equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception err){
            System.out.println(err.toString());
            return false;
        }
    }

    public static String GetSpecificIndexDetail (String response ,String dataParam, String param , int index ){
        try{
//            PrintUtil.PrintSuccessLog(response);
            JsonPath jsonPath = new JsonPath(response);
            return jsonPath.getString("data"+dataParam+"["+index+"]." + param);  // data param filters to the param after data
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }

    public static String extractFirstFieldFromList(String response, String dataKey, String arrayKey, String fieldToExtract ) {
        try {
            JSONObject json = new JSONObject(response);
            JSONObject dataObject = json.getJSONObject(dataKey);
//            PrintUtil.PrintSuccessLog("Data Object : "+dataObject);
            JSONArray array = dataObject.getJSONArray(arrayKey);


            if (array.length() > 0) {
                JSONObject firstObject = array.getJSONObject(0);
                return firstObject.getString(fieldToExtract);
            } else {
                throw new RuntimeException("No objects found in array: " + arrayKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    public static String extractNestedArrayIndex (String response, String dataKey,String arrayKey,String subArrayKey, String fieldToExtract ){
        try{
            JSONObject json = new JSONObject(response);
            JSONObject dataObject = json.getJSONObject(dataKey);
            return dataObject
                    .getJSONArray(arrayKey)
                    .getJSONObject(0)  // Get the first location
                    .getJSONArray(subArrayKey)  // Get departments array
                    .getJSONObject(0)  // Get the first department
                    .getString(fieldToExtract);
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
            return err.toString();
        }
    }
}
