@KafkaListener(topics = "relational-service-topic", groupId = "relational-service-group")
public void handleRelationalService(String message) {
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