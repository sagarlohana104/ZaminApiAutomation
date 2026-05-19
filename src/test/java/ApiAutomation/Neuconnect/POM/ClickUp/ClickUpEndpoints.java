package ApiAutomation.Neuconnect.POM.ClickUp;

public class ClickUpEndpoints
{
    //space
    public static String GetSpaceById = "/api/v2/space";

    // authentication
    public static String GetAccessToken = "/api/v2/oauth";

    // list
    public static String CreateList = "/api/v2";
    public static String GetList= "/api/v2";
    public static String DeleteList = "/api/v2";

    // task
    public static String CreateTask = "/api/v2";
    public static String GetTaskByListId ="/api/v2";
}
