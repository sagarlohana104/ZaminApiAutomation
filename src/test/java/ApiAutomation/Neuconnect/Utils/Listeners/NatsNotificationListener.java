package ApiAutomation.Neuconnect.Utils.Listeners;

import ApiAutomation.Neuconnect.Utils.env.envConfig;
import io.nats.client.*;
import io.nats.client.api.AckPolicy;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class NatsNotificationListener
{
    private static final String NATS_URL = envConfig.getEnv("NATS_URL");
    private static final String USERNAME = envConfig.getEnv("USERNAME2");
    private static final String PASSWORD = envConfig.getEnv("PASSWORD")+"#";
    private static final String STREAM_NAME = envConfig.getEnv("STREAM_NAME");

    private Connection connection;
    private JetStream jetStream;

    public void connect() throws Exception {
//        PrintUtil.PrintSuccessLog("VALUES: "+NATS_URL+USERNAME+PASSWORD+STREAM_NAME);
        Options options = new Options.Builder()
                .server(NATS_URL)
                .userInfo(USERNAME.toCharArray(), PASSWORD.toCharArray())
                .connectionTimeout(Duration.ofSeconds(5))
                .build();
        connection = Nats.connect(options);
        jetStream = connection.jetStream();

        ensureStreamExists();
        System.out.println("✅ Connected to NATS and JetStream is ready.");
    }

    public void subscribe(String subject, NotificationHandler handler) throws Exception {
        String fullSubject = "notifications." + subject;
        String durable = "durable-" + subject + "-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        ConsumerConfiguration consumerConfig = ConsumerConfiguration.builder()
                .durable(durable)
                .ackPolicy(AckPolicy.Explicit)
                .build();

        PushSubscribeOptions options = PushSubscribeOptions.builder()
                .stream(STREAM_NAME)
                .configuration(consumerConfig)
                .durable(durable)
                .build();

        JetStreamSubscription subscription = jetStream.subscribe(fullSubject, options);

        // Spawn a background thread to receive and process messages
        new Thread(() -> {
            try {
                while (true) {
                    Message msg = subscription.nextMessage(Duration.ofSeconds(10));
                    if (msg != null) {
                        String content = new String(msg.getData(), StandardCharsets.UTF_8);
                        handler.onMessage(content);
                        msg.ack(); // Acknowledge after handling
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("👂 Subscribed and listening to subject: " + fullSubject);
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            connection.close();
            System.out.println("🔌 Disconnected from NATS.");
        }
    }

    private void ensureStreamExists() throws Exception {
        JetStreamManagement jsm = connection.jetStreamManagement();
        try {
            StreamInfo info = jsm.getStreamInfo(STREAM_NAME);
        } catch (JetStreamApiException e) {
            StreamConfiguration config = StreamConfiguration.builder()
                    .name(STREAM_NAME)
                    .subjects("notifications.*")
                    .build();
            jsm.addStream(config);
            System.out.println("📌 Created stream: " + STREAM_NAME);
        }
    }

    @FunctionalInterface
    public interface NotificationHandler {
        void onMessage(String message);
    }
}
