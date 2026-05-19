package ApiAutomation.Neuconnect.Utils;

import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class SearchInResponse
{
    public static String findValueInResponse(String response, String searchKey) {
        if (response == null || response.trim().isEmpty()) {
            throw new IllegalArgumentException("Response is null or empty");
        }

        if (!response.trim().startsWith("{")) {
            throw new IllegalArgumentException("Invalid JSON format: Response must begin with '{'");
        }

        JSONObject jsonResponse = new JSONObject(response);
        return findValue(jsonResponse, searchKey);
    }

    private static String findValue(JSONObject jsonObject, String searchKey) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            // Check if the key matches the searchKey
            if (key.equals(searchKey)) {
                return value.toString();
            }

            // Recursively search in nested JSONObjects
            if (value instanceof JSONObject) {
                String foundValue = findValue((JSONObject) value, searchKey);
                if (foundValue != null) return foundValue;
            }

            // Recursively search in each element of a JSONArray
            if (value instanceof JSONArray) {
                String foundValue = searchInArray((JSONArray) value, searchKey);
                if (foundValue != null) return foundValue;
            }
        }
        return null; // Return null if the key is not found
    }

    private static String searchInArray(JSONArray array, String searchKey) {
        for (int i = 0; i < array.length(); i++) {
            Object item = array.get(i);

            // Search each JSONObject inside the array
            if (item instanceof JSONObject) {
                String foundValue = findValue((JSONObject) item, searchKey);
                if (foundValue != null) return foundValue;
            }

            // If the array contains nested arrays, search recursively
            if (item instanceof JSONArray) {
                String foundValue = searchInArray((JSONArray) item, searchKey);
                if (foundValue != null) return foundValue;
            }
        }
        return null;
    }
    public static boolean isTermPresentInResponse(String responseBody, String jsonPathTerm, String... termsToCheck) {
        // Parse the response String to JSON
        JsonPath jsonPath = new JsonPath(responseBody);

        // Extract the list of values based on the provided JSON path
        List<String> values = jsonPath.getList(jsonPathTerm);

        // Check if all terms exist in the extracted values
        for (String term : termsToCheck) {
            if (!values.contains(term)) {
                return false; // Return false if any term is missing
            }
        }
        return true; // Return true only if all terms are present
    }

    public static boolean isRoleIdPresentInResponse(String responseBody, String jsonPathTerm, String roleIdToCheck) {
        // Parse the response String to JSON
        JsonPath jsonPath = new JsonPath(responseBody);

        // Extract the list of role IDs based on the provided JSON path
        List<String> roleIds = jsonPath.getList(jsonPathTerm);

        // Check if the given roleId exists in the list
        return roleIds.contains(roleIdToCheck);
    }

    public static String getRoleIdByRoleName(String response, String roleName) {
        // Parse the response JSON
        JSONObject jsonResponse = new JSONObject(response);

        // Check if the "data" key exists
        if (!jsonResponse.has("data")) {
            System.err.println("The response does not contain a 'data' key.");
            return null;
        }

        // Handle "data" as either a JSONArray or a JSONObject
        Object data = jsonResponse.get("data");
        if (data instanceof JSONArray) {
            // If "data" is a JSONArray
            JSONArray dataArray = (JSONArray) data;
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject role = dataArray.getJSONObject(i);
                if (role.getString("name").equalsIgnoreCase(roleName)) {
                    return role.getString("id");
                }
            }
        } else if (data instanceof JSONObject) {
            // If "data" is a JSONObject
            JSONObject role = (JSONObject) data;
            if (role.getString("name").equalsIgnoreCase(roleName)) {
                return role.getString("id");
            }
        } else {
            System.err.println("Unexpected data format: 'data' is neither a JSONArray nor a JSONObject.");
        }

        // Return null if no matching roleName is found
        return null;
    }

    public static String getUserIdByUserName(String response, String roleName) {
        // Parse the response JSON
        JSONObject jsonResponse = new JSONObject(response);
        JSONArray dataArray = jsonResponse.getJSONArray("data");

        // Loop through the data array to find the matching role name
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject role = dataArray.getJSONObject(i);
            if (role.getString("name").equalsIgnoreCase(roleName)) {
                return role.getString("userId");
            }
        }
        return null; // Return null if no matching roleName is found
    }

    public static boolean verifyIfAccountIsFavourite(String response, String accountGuid) {
        try {
            // Parse the response string into a JSON object
            JSONObject jsonObject = new JSONObject(response);

            // Ensure "data" key exists and is an array
            if (jsonObject.has("data") && jsonObject.get("data") instanceof JSONArray) {
                JSONArray dataArray = jsonObject.getJSONArray("data");

                // Iterate through each item in the array
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject itemObject = dataArray.getJSONObject(i);

                    // Check if the accountId and isFavorite fields are present
                    if (itemObject.has("accountId") && itemObject.has("isFavorite")) {
                        String responseAccountGuid = itemObject.getString("accountId");
                        boolean isFavorite = itemObject.getBoolean("isFavorite");

                        // Check if the accountId matches and return the isFavorite status
                        if (responseAccountGuid.equals(accountGuid)) {
                            return isFavorite;  // Return true or false based on the isFavorite field
                        }
                    }
                }
            }

            return false;  // Return false if the accountGuid is not found or is not marked as favorite
        } catch (Exception err) {
            System.out.println("Error: " + err.toString());
            return false;  // Return false in case of an exception
        }
    }
}
