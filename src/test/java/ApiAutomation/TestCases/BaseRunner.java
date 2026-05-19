package ApiAutomation.TestCases;

import ApiAutomation.Neuconnect.POM.ClickUp.ClickUp.ClickUpFunctions;
import ApiAutomation.Neuconnect.POM.ClickUp.ClickUp.ClickUpGenerics;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.DateUtils;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseRunner
{
    private static final String clickupToken = envConfig.getEnv("CLICKUP_API_TOKEN");
    private static final String clickupFolderId = Constants.ClickUpFolderId;

    public BaseRunner(){
        // Get the name of the actual subclass (Runner file)
        String runnerClassName = this.getClass().getSimpleName();
        String listName;

        if (runnerClassName.equals("GRPCRunner")) {
            listName = "GRPC_REGRESSION_";
        } else if (runnerClassName.equals("SomeOtherRunner")) {
            listName = "OTHER_SERVICE_REGRESSION_";
        } else {
            listName = "Default Automation List";
        }

        // Set system property
        System.setProperty("clickup.list.name", listName);
        PrintUtil.PrintSuccessLog("✅ ClickUp List Name Set: " + listName);
    }

    @BeforeClass
    public static void setupBeforeRunnerExecution() {
        PrintUtil.PrintSuccessLog("🔹 Automation execution started. Setting up configurations...");

        // Get the dynamically set list name
        String listName = System.getProperty("clickup.list.name", "Default_Automation_List");

        RestAssured.baseURI= envConfig.getEnv("CLICKUP_URL");

        // create list
        PrintUtil.PrintSuccessLog("CREATING GRPC LIST : "+ ClickUpFunctions.CreateList(envConfig.getEnv("CLICKUP_API_TOKEN"), Constants.ClickUpFolderId,listName+" - "+ DateUtils.getDateTime()));

    }

    @AfterClass
    public static void terminate() {
        PrintUtil.PrintSuccessLog("🔹 Automation termination started. Setting up configurations...");
        try {
            RestAssured.baseURI = envConfig.getEnv("CLICKUP_URL");
            RestAssured.port=-1;
//            PrintUtil.PrintSuccessLog("CLICK UP FOLDER ID : "+clickupFolderId);
//            PrintUtil.PrintSuccessLog("CLICK UP ACCESS TOKEN :"+clickupToken);
//            PrintUtil.PrintSuccessLog("RESPONSE : "+ClickUpFunctions.GetList(clickupToken, clickupFolderId));
            String listId = GenericExtractorsValidators.getLastIndexDetail(
                    ClickUpFunctions.GetList(clickupToken, clickupFolderId),
                    "lists", "id", "0"
            );

            if (!ClickUpGenerics.checkListContainsTask(ClickUpFunctions.GetTaskByListId(clickupToken, listId))) {
                PrintUtil.PrintSuccessLog(ClickUpFunctions.DeleteList(clickupToken,listId));
                PrintUtil.PrintSuccessLog("🗑️ Empty ClickUp list deleted successfully: " + listId);
            } else {
                PrintUtil.PrintWarningLog("📋 List has tasks. Not deleting: " + listId);
            }

        } catch (Exception e) {
            PrintUtil.PrintErrorLog("🚨 Exception in @After hook (ClickUp cleanup): " + e.getMessage());
        }
    }
}
