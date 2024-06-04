@Service
public class GraphDatabaseService {

    @KafkaListener(topics = "graph-service-topic", groupId = "graph-service-group")
    public void handleGraphService(String message) {
        // Deserialize the message
        // Perform database operations
        // If successful, send a success message
        // If failed, send a fail message

        try {
            // Assume some operation here
            kafkaTemplate.send("finish-success", message);
        } catch (Exception e) {
            kafkaTemplate.send("finish-fail", message);
        }
    }
}
