package ApiAutomation.Neuconnect.Utils.Asserts;

import ApiAutomation.Neuconnect.POM.ClickUp.ClickUp.ClickUpFunctions;
import ApiAutomation.Neuconnect.Utils.Api.ApiResponseUtils;
import ApiAutomation.Neuconnect.Utils.Api.GenericExtractorsValidators;
import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.restassured.RestAssured;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertUtils
{
    private static String accessToken,listId ;
    private static List<String> listOfAssignees;


    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken() {
        accessToken = envConfig.getEnv("CLICKUP_API_TOKEN");
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("⚠️ CLICKUP_ACCESS_TOKEN is not set in environment variables!");
        }
    }

    public static String getListId() {
        return listId;
    }

    public static List<String> getListOfAssignees() {
        return listOfAssignees;
    }

    public static void setListOfAssignees(List<String> listOfAssignees) {
        AssertUtils.listOfAssignees = listOfAssignees;
    }

    public static void setListId(String listId) {
        AssertUtils.listId = listId;
    }
    static {  // works without object initialization , since it is in a static block
        setAccessToken();
        RestAssured.baseURI=envConfig.getEnv("CLICKUP_URL");
        RestAssured.port=-1;
        setListId(GenericExtractorsValidators.getLastIndexDetail(ClickUpFunctions.GetList(getAccessToken(), Constants.ClickUpFolderId), "lists", "id", "0"));
        setListOfAssignees(Collections.singletonList(Constants.MaaraibTeamId));
    }

    private static void handleAssertionError(String testCaseName, String errorMessage) {
        RestAssured.baseURI=envConfig.getEnv("CLICKUP_URL");
        RestAssured.port=-1;
        PrintUtil.PrintErrorLog("❌ Test Case Failed: " + testCaseName + " | Reason: " + errorMessage);
//        PrintUtil.PrintSuccessLog("LIST ID : "+getListId());
//        PrintUtil.PrintSuccessLog("ACCESS TOKEN : "+getAccessToken());
//        PrintUtil.PrintSuccessLog("BASE URI : "+RestAssured.baseURI);
//        PrintUtil.PrintSuccessLog("PORT: "+RestAssured.port);
        PrintUtil.PrintSuccessLog("CREATING TASK : " + ClickUpFunctions.CreateTask(getAccessToken(), Constants.ClickUpFolderId, getListId(), "❌ Test Case '"+testCaseName+"' Failed ❌", errorMessage, getListOfAssignees(), false));
    }



    public static void verifiesToReceiveIsApiHandled (String expectedIsApiHandled , String response ,String testcaseName){
        try{
//            PrintUtil.PrintSuccessLog("ACTUAL RESPONSE : "+ response);
            boolean actualIsApiHandled = ApiResponseUtils.getApiResponseIsApiHandled(response);
            boolean expectedIsApiHandledBoolean = Constants.TRUE.equalsIgnoreCase(expectedIsApiHandled);
//            PrintUtil.PrintSuccessLog("LALA"+actualIsApiHandled + "    "+expectedIsApiHandledBoolean );
            assertEquals(
                    expectedIsApiHandledBoolean,
                    actualIsApiHandled,
                    "Mismatch in isApiHandled. Expected: " + expectedIsApiHandled + ", but got: " + actualIsApiHandled
            );
        }
        catch (AssertionError err) {
            handleAssertionError(testcaseName, "ERROR : "+ err.toString()+"\n"+" ERROR MESSAGE : "+err.getMessage()+"\n"+" API RESPONSE : "+response);
            throw err;  // Rethrow the exception to mark the test case as failed in reports
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
        }
    }

    public static void verifiesToReceiveIsRequestSuccess (String expectedIsRequestSuccess , String response,String testcaseName){
        try{
            boolean actualIsRequestSuccess = ApiResponseUtils.getApiResponseIsRequestSuccess(response);
            boolean expectedIsRequestSuccessBoolean= Constants.TRUE.equalsIgnoreCase(expectedIsRequestSuccess);

            assertEquals(
                    expectedIsRequestSuccessBoolean,
                    actualIsRequestSuccess,
                    "Mismatch in isApiHandled. Expected: " + expectedIsRequestSuccess + ", but got: " + actualIsRequestSuccess
            );
        }
        catch (AssertionError err) {
            handleAssertionError(testcaseName, "ERROR : "+ err.toString()+"\n"+" ERROR MESSAGE : "+err.getMessage()+"\n"+" API RESPONSE : "+response);
            throw err;  // Rethrow the exception to mark the test case as failed in reports
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
        }
    }

    public static void verifiesToReceiveStatusCode (int expectedStatusCode, String response ,String testcaseName){
        try{
            int actualStatusCode = ApiResponseUtils.getApiResponseStatusCode(response);
            assertEquals(expectedStatusCode, actualStatusCode, "Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode);
        }
        catch (AssertionError err) {
            handleAssertionError(testcaseName, "ERROR : "+ err.toString()+"\n"+" ERROR MESSAGE : "+err.getMessage()+"\n"+" API RESPONSE : "+response);
            throw err;  // Rethrow the exception to mark the test case as failed in reports
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
        }
    }

    public static void verifiesToReceiveMessage (String expectedMessage , String response,String testcaseName){
        try{
            String actualMessage = ApiResponseUtils.getApiResponseMessage(response).trim();
            assertEquals(expectedMessage, actualMessage, "Expected Message: " + expectedMessage + ", but got: " + actualMessage);

        }
        catch (AssertionError err) {
            handleAssertionError(testcaseName, "ERROR : "+ err.toString()+"\n"+" ERROR MESSAGE : "+err.getMessage()+"\n"+" API RESPONSE : "+response);
            throw err;  // Rethrow the exception to mark the test case as failed in reports
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
        }
    }

    public static void verifiesToReceiveException (String exception , String expectedExceptionMessage , String response ,String testcaseName){
        try{
            String actualExceptionMessage = ApiResponseUtils.getApiResponseException(response,exception);
            PrintUtil.PrintSuccessLog("ACTUAL EXCEPTION : "+ actualExceptionMessage);
            assertEquals(expectedExceptionMessage, actualExceptionMessage, "Expected Exception: " + expectedExceptionMessage + ", but got: " + actualExceptionMessage);
        }
        catch (AssertionError err) {
            handleAssertionError(testcaseName, "ERROR : "+ err.toString()+"\n"+" ERROR MESSAGE : "+err.getMessage()+"\n"+" API RESPONSE : "+response);
            throw err;  // Rethrow the exception to mark the test case as failed in reports
        }
        catch (Exception err){
            PrintUtil.PrintErrorLog(err.toString());
        }
    }


}
