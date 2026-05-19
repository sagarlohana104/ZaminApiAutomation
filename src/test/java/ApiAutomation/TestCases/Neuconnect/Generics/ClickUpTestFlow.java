package ApiAutomation.TestCases.Neuconnect.Generics;

import ApiAutomation.Neuconnect.POM.ClickUp.ClickUp.ClickUpFunctions;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class ClickUpTestFlow
{
    private static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken() {
        accessToken = envConfig.getEnv("CLICKUP_API_TOKEN");
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("⚠️ CLICKUP_ACCESS_TOKEN is not set in environment variables!");
        }
    }

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = envConfig.getEnv("CLICKUP_URL");
        setAccessToken();
    }

    @Test
    public void testGetSpace (){
        PrintUtil.PrintSuccessLog(ClickUpFunctions.GetSpaceById(getAccessToken(), Constants.AjeekWorkspaceId    ,true));
        // workspace GET working perfectly

    }

    @Test
    public void createList (){
        PrintUtil.PrintSuccessLog(ClickUpFunctions.CreateList(getAccessToken(),"90184381481","ALLAAL"));
    }

    @Test
    public void getList (){
        PrintUtil.PrintSuccessLog(ClickUpFunctions.GetList(getAccessToken(), Constants.ClickUpFolderId));
    }


    @Test
    public void createTask (){
        List<String> list = Collections.singletonList(Constants.MaaraibTeamId);
        PrintUtil.PrintSuccessLog(ClickUpFunctions.CreateTask(getAccessToken(),Constants.ClickUpFolderId,"901806803177","NAME IT IS","DESCRIPTION IT IS ",list ,false));
    }

    @Test
    public void getlistById (){
        PrintUtil.PrintSuccessLog(GenericExtractorsValidators.getLastIndexDetail(ClickUpFunctions.GetList(getAccessToken(), Constants.ClickUpFolderId), "lists", "id", "0"));

    }

    @Test
    public void DeleteLists (){
        for ( int i=0 ; i<50 ; i++){
            String listId= GenericExtractorsValidators.getLastIndexDetail(ClickUpFunctions.GetList(getAccessToken(), Constants.ClickUpFolderId), "lists", "id", "0");
            PrintUtil.PrintSuccessLog("DELETE ENDPOINT : "+ClickUpFunctions.DeleteList(getAccessToken(),listId));
        }
    }

    @Test
    public void GetTaskByListId (){
        PrintUtil.PrintSuccessLog(ClickUpFunctions.GetTaskByListId(getAccessToken(),"901806794623"));
    }
}
