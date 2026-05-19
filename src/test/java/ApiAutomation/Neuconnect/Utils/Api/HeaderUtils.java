package ApiAutomation.Neuconnect.Utils.Api;

import ApiAutomation.Neuconnect.Utils.PrintUtil;

import java.util.HashMap;
import java.util.Map;

public class HeaderUtils
{
    public static Map<String,String> getHeaders (){
        try{
            Map<String, String> headers = new HashMap<>();
            headers.put("Custom-Header", "MyValue");

            return headers;
        }
        catch (Exception err){
            PrintUtil.PrintSuccessLog(err.toString());
            return null;
        }
    }
}
