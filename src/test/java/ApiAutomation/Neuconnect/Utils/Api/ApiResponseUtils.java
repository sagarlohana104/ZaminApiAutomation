package ApiAutomation.Neuconnect.Utils.Api;

import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiResponseUtils
{
    public static String getApiResponseException (String response , String exception  ){
        try{
            JsonPath jsonPathEvaluator = new JsonPath(response);
            return jsonPathEvaluator.getString("exception."+exception);
        }
        catch (Exception err)
        {
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }

    }


    public static int getApiResponseStatusCode (String response ){
        try{
            //System.out.println(response);
            JsonPath jsonPathEvaluator = new JsonPath(response);
            return jsonPathEvaluator.getInt("statusCode");
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return 0;
        }
    }

    public static String getApiResponseDataDetails (String response , String param){
        try{
            JsonPath jsonPathEvaluator = new JsonPath(response);
            return jsonPathEvaluator.getString("data."+param);
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }
    }

    public static String getApiResponseMessage (String response){
        try{
            //System.out.println("this "+response);
            JsonPath jsonPathEvaluator = new JsonPath(response);
            return jsonPathEvaluator.getString("message");
        }
        catch (Exception err)
        {
            PrintUtil.PrintErrorLog(err.getMessage());
            return err.toString();
        }

    }

    public static boolean getApiResponseIsApiHandled (String response){
        try{
//            System.out.println("this "+response);
            JsonPath jsonPathEvaluator = new JsonPath(response);
            return jsonPathEvaluator.getBoolean("isApiHandled");
        }
        catch (Exception err)
        {
            PrintUtil.PrintErrorLog(err.getMessage());
            return false;
        }

    }

    public static boolean getApiResponseIsRequestSuccess (String response){
        try{
            //System.out.println("this "+response);
            JsonPath jsonPathEvaluator = new JsonPath(response);
            return jsonPathEvaluator.getBoolean("isRequestSuccess");
        }
        catch (Exception err)
        {
            PrintUtil.PrintErrorLog(err.getMessage());
            return false;
        }

    }

    public static String extractExternalApiResponse(String response) {
        try {
            JSONObject jsonResponse = new JSONObject(response);

            // Get the 'data' object and then 'externalAPiResponse' array
            JSONObject dataObject = jsonResponse.getJSONObject("data");
            JSONArray externalApiResponseArray = dataObject.getJSONArray("externalAPiResponse");
            return externalApiResponseArray.get(0).toString();

//            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray GetDataArray (String response){
        try{
            JSONObject responseJson = new JSONObject(response);
            return  responseJson.getJSONArray("data");
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return null;
        }
    }
}
