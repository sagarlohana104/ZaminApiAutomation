package ApiAutomation.Neuconnect.Utils;

import io.restassured.RestAssured;

public class PortUtils {
    public static void setPort(int port) {
        RestAssured.port=port;


    }

    public static int getPort()
    {
        return  RestAssured.port;
    }
}