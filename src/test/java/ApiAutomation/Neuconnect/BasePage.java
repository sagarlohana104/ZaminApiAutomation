package ApiAutomation.Neuconnect;

import ApiAutomation.Neuconnect.Utils.Constants;
import ApiAutomation.Neuconnect.Utils.PrintUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Objects;

public class BasePage {


    public static String MakeApiCall(String payload, String endpoint, String bearerToken, String HTTPmethod, Boolean paramBool, String paramVariable, String paramValue) {
        try {
            Response response = null;

            if (Objects.equals(HTTPmethod, Constants.POST)) {
                if (paramBool) {
                    response = RestAssured
                            .given()
                                .contentType("application/json")
                                .body(Objects.equals(payload, Constants.EMPTY_PAYLOAD) ? "{}" : payload)
                            .when()
                                .post(endpoint + paramVariable + paramValue)
                            .then()
                                .extract()
                                .response();
                } else {
                    response = RestAssured
                            .given()
                            .contentType("application/json")
                            .header("Authorization", "Bearer " + bearerToken)
                            .body(Objects.equals(payload, Constants.EMPTY_PAYLOAD) ? "{}" : payload)
                            .when()
                            .post(endpoint)
                            .then()
                            .extract()
                            .response();
                }

            } else if (Objects.equals(HTTPmethod, Constants.GET)) {
                if (paramBool) {
                    response = RestAssured
                            .given()
                            .contentType("application/json")
                            .header("Authorization", "Bearer " + bearerToken)
                            .when()
                            .get(endpoint + paramVariable + paramValue)
                            .then()
                            .extract()
                            .response();
                } else {
                    response = RestAssured
                            .given()
                            .contentType("application/json")
                            .header("Authorization", "Bearer " + bearerToken)
                            .when()
                            .get(endpoint)
                            .then()
                            .extract()
                            .response();
                }
            } else if (Objects.equals(HTTPmethod, Constants.PATCH)) {
                response = RestAssured
                        .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(Objects.equals(payload, Constants.EMPTY_PAYLOAD) ? "{}" : payload)
                        .when()
                        .patch(endpoint)
                        .then()
//                        .statusCode(200)
                        .extract()
                        .response();
            } else if (Objects.equals(HTTPmethod, Constants.PUT)) {
//                PrintUtil.PrintSuccessLog("fee");
                response = RestAssured
                        .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .body(Objects.equals(payload, Constants.EMPTY_PAYLOAD) ? "{}" : payload)
                        .when()
                        .put(endpoint)
                        .then()
//                        .statusCode(200)
                        .extract()
                        .response();
            } else if (Objects.equals(HTTPmethod, Constants.DELETE)) {
                response = RestAssured
                        .given()
                        .contentType("application/json")
                        .header("Authorization", "Bearer " + bearerToken)
                        .when()
                        .delete(endpoint)
                            .then()
    //                        .statusCode(200)
                            .extract()
                            .response();
                }
                return response.getBody().asString();
            } catch (Exception err) {
                PrintUtil.PrintErrorLog(err.toString());
                return err.toString();
            }
        }


    public static String makeApiCall2(
            String endpoint,
            String httpMethod,
            String apiToken,
            String paramVariable,
            String paramValue,
            String payload) {

        try {
            RequestSpecification request = RestAssured.given()
                    .contentType("application/json")
                    .header("Authorization", apiToken);  // ✅ No "Bearer" for API token

            if (payload != null && !payload.isEmpty()) {
                request.body(payload);
            }

            Response response = switch (httpMethod.toUpperCase()) {
                case "POST" -> request.post(endpoint+paramVariable+paramValue);
                case "GET" -> request.get(endpoint+paramVariable+paramValue);
                case "PUT" -> request.put(endpoint+paramVariable+paramValue);
                case "PATCH" -> request.patch(endpoint+paramVariable+paramValue);
                case "DELETE" -> request.delete(endpoint+paramVariable+paramValue);
                default -> throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
            };

            return response.getBody().asString();
        } catch (Exception err) {
            PrintUtil.PrintErrorLog("API Request Failed: " + err.getMessage());
            return null;
        }
    }
}

