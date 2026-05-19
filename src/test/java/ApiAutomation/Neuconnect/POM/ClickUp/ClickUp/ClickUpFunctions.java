package ApiAutomation.Neuconnect.POM.ClickUp.ClickUp;

import ApiAutomation.Neuconnect.BasePage;
import ApiAutomation.Neuconnect.POM.ClickUp.ClickUpEndpoints;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickUpFunctions extends BasePage
{
    public static String GetSpaceById (String bearerToken,String spaceId , boolean isArchived ){
        try{
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("archived", "false");

            return makeApiCall2(ClickUpEndpoints.GetSpaceById,Constants.GET,bearerToken,"/",spaceId,null);
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }

    public static String CreateTask (String bearerToken,String folderId, String listId, String taskName , String taskDescription , List<String> assignees, boolean notify_all){
        try {
            JSONObject payload = new JSONObject()
                    .put("name",taskName)
                    .put("description",taskDescription)
                    .put("assignees",assignees)
                    .put("notify_all",notify_all);
            PrintUtil.PrintSuccessLog("PAYlOAD : "+ payload.toString());
            return makeApiCall2(ClickUpEndpoints.CreateTask,Constants.POST,bearerToken,"/list/"+listId+"/task?team_id="+folderId,"",payload.toString());

        }catch (Exception err){
                PrintUtil.PrintSuccessLog(err.toString());
                return err.toString();
        }
    }

    public static String GetTaskByListId (String bearerToken , String listId){
        try{
            return makeApiCall2(ClickUpEndpoints.GetTaskByListId,Constants.GET,bearerToken,"/list/"+listId+"/task","","");
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }

    public static String CreateList (String bearerToken,String folderId , String name){
        try{
            JSONObject payload= new JSONObject()
                    .put("name",name);
            return makeApiCall2(ClickUpEndpoints.CreateList,Constants.POST,bearerToken,"/folder/"+folderId+"/list","",payload.toString());
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }

    public static String GetList (String bearerToken , String folderId){
        try{
            return makeApiCall2(ClickUpEndpoints.GetList,Constants.GET,bearerToken,"/folder/"+folderId+"/list","","");
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }



    public static String GetAccessToken (String clientId , String clientSecret){
        try{
            return MakeApiCall(Constants.EMPTY_PAYLOAD,ClickUpEndpoints.GetAccessToken,Constants.EMPTY_PAYLOAD,Constants.POST,true,"","");
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }

    public static String DeleteList (String bearerToken , String listId){
        try{
            return makeApiCall2(ClickUpEndpoints.DeleteList,Constants.DELETE,bearerToken,"/list/"+listId,"","");
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return err.toString();
        }
    }


}
