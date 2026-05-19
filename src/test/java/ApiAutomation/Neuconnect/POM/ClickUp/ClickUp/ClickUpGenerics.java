package ApiAutomation.Neuconnect.POM.ClickUp.ClickUp;

import ApiAutomation.Neuconnect.Utils.PrintUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClickUpGenerics
{
    public static Boolean checkListContainsTask (String response){
        try{
            JSONObject json = new JSONObject(response);
            JSONArray tasks = json.getJSONArray("tasks");
            return !tasks.isEmpty();
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return false;
        }
    }
}
