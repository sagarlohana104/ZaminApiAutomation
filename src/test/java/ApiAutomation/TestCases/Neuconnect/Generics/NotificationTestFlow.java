package ApiAutomation.TestCases.Neuconnect.Generics;

import ApiAutomation.Neuconnect.POM.Auth.Authentication.AuthGenerics;
import ApiAutomation.Neuconnect.POM.Auth.Authentication.Login;
import ApiAutomation.Neuconnect.Utils.Credentials;
import ApiAutomation.Neuconnect.Utils.Listeners.NatsNotificationListener;
import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class NotificationTestFlow
{
    @BeforeAll
    public static void setup() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = envConfig.getEnv("BASE_URL");
        RestAssured.port = envConfig.getEnvInteger("PORT");
    }

    @Test
    public void sendAndAssertNotification() throws Exception {

        NatsNotificationListener listener = new NatsNotificationListener();
        listener.connect();

        // We use this to wait until the message arrives
        CountDownLatch latch = new CountDownLatch(1);
        final String[] receivedMessage = {null};

        listener.subscribe("hvWjqrc6pd", message -> {
            System.out.println("📩 Notification received: " + message);
            receivedMessage[0] = message;
            latch.countDown();  // release the wait once we receive the message
        });


        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
//        PrintUtil.PrintSuccessLog(
//                NotificationFunctions.SendAndSaveNotification(jwtToken,"Hello SQA TESTING NATS BY CODE","TITLE","CRMPartner","ChatScreenSQA","SQA","hvWjqrc6pd","","","hvWjqrc6pd","CRMPartner")
//        );
        String actualMessage=new JsonPath(receivedMessage[0]).getString("Message");
        String actualTitle=new JsonPath(receivedMessage[0]).getString("Title");
        String actualTargetNameSpace=new JsonPath(receivedMessage[0]).getString("TargetNamespace");
        String actualTag=new JsonPath(receivedMessage[0]).getString("Tag");
        boolean messageArrived = latch.await(10, TimeUnit.SECONDS);
        Assertions.assertTrue(messageArrived, "❌ Notification was not received in time.");

        Assertions.assertEquals("Hello SQA TESTING NATS BY CODE", actualMessage, "MISMATCH IN MESSAGE SEND AND RECIEVED");
        Assertions.assertEquals("SQA", actualTag, "MISMATCH IN MESSAGE SEND AND RECIEVED");
        Assertions.assertEquals("ChatScreenSQA", actualTargetNameSpace, "MISMATCH IN MESSAGE SEND AND RECIEVED");
        Assertions.assertEquals("TITLE", actualTitle, "MISMATCH IN MESSAGE SEND AND RECIEVED");


        listener.disconnect();
    }


    @Test
    public void ListenerConnect() throws Exception {

        NatsNotificationListener listener = new NatsNotificationListener();
        listener.connect();

        // We use this to wait until the message arrives
        CountDownLatch latch = new CountDownLatch(1);
        final String[] receivedMessage = {null};

        listener.subscribe("00zvpYQH54", message -> {
            System.out.println("📩 Notification received: " + message);
            receivedMessage[0] = message;
            latch.countDown();  // release the wait once we receive the message
        });

        new CountDownLatch(1).await();
    }

    @Test
    public void ListenerConnect2() throws Exception {

        NatsNotificationListener listener = new NatsNotificationListener();
        listener.connect();

        // We use this to wait until the message arrives
        CountDownLatch latch = new CountDownLatch(1);
        final String[] receivedMessage = {null};

        listener.subscribe("72990663-2edc-4c10-b331-cd1c65e477e0", message -> {
            System.out.println("📩 Notification received: " + message);
            receivedMessage[0] = message;
            latch.countDown();  // release the wait once we receive the message
        });

        new CountDownLatch(1).await();
    }

    @Test
    public void ListenerConnect3() throws Exception {

        NatsNotificationListener listener = new NatsNotificationListener();
        listener.connect();

        // We use this to wait until the message arrives
        CountDownLatch latch = new CountDownLatch(1);
        final String[] receivedMessage = {null};

        listener.subscribe("bKTWTdikTd", message -> {
            System.out.println("📩 Notification received: " + message);
            receivedMessage[0] = message;
            latch.countDown();  // release the wait once we receive the message
        });

        new CountDownLatch(1).await();
    }


    @Test
    public void ListenerConnect4() throws Exception {

        NatsNotificationListener listener = new NatsNotificationListener();
        listener.connect();

        // We use this to wait until the message arrives
        CountDownLatch latch = new CountDownLatch(1);
        final String[] receivedMessage = {null};

        listener.subscribe("00zvpYQH54", message -> {
            System.out.println("📩 Notification received: " + message);
            receivedMessage[0] = message;
            latch.countDown();  // release the wait once we receive the message
        });

        new CountDownLatch(1).await();
    }

    @Test
    public void SendOnlyNotification(){
        String jwtToken = AuthGenerics.getJwtToken(Login.login(Credentials.SuperAdminEmail,Credentials.SuperAdminPass));
//        PrintUtil.PrintSuccessLog(
//                NotificationFunctions.SendAndSaveNotification(jwtToken,"HELLLLLLLOOOOOO KESAY HOOOOOOOOO","TITLE","CRMPartner","TicketList","Notification","7JOUj9hBjF","","","72990663-2edc-4c10-b331-cd1c65e477e0","CRMPartner")
//        );
    }


}
