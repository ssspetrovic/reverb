package rs.ac.uns.acs.nais.OrchestratorService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrchestratorService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrchestratorService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${spring.kafka.topics.start-saga}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleStartSaga(String message) {
        // Deserialize the message and start the saga
        // Here, you'd have the logic to send messages to the other services
        // For example, let's assume the message contains userId and songId

        // Notify relational service
        kafkaTemplate.send("relational-service-topic", message);

        // Notify graph service
        kafkaTemplate.send("graph-service-topic", message);
    }

    @KafkaListener(topics = "${spring.kafka.topics.finish-success}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleFinishSuccess(String message) {
        // Handle successful transaction
        System.out.println("Transaction successful: " + message);
    }

    @KafkaListener(topics = "${spring.kafka.topics.finish-fail}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleFinishFail(String message) {
        // Handle failed transaction
        System.out.println("Transaction failed: " + message);
    }
}
