package rs.ac.uns.acs.nais.orchestratorservice.service;

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

    public void startSaga(String userId, String songId) {
        kafkaTemplate.send("start-saga", String.format("Start saga for user %s and song %s", userId, songId));
    }

    @KafkaListener(topics = "start-saga", groupId = "orchestrator-group")
    public void handleStartSaga(String message) {
        // Handle start saga
        // Communicate with relational and graph services
        System.out.println("Received: " + message);

        // Simulate successful operation
        kafkaTemplate.send("finish-success", "Saga finished successfully");
    }

    @KafkaListener(topics = "finish-success", groupId = "orchestrator-group")
    public void handleFinishSuccess(String message) {
        // Handle successful completion of the saga
        System.out.println("Saga finished successfully: " + message);
    }

    @KafkaListener(topics = "finish-fail", groupId = "orchestrator-group")
    public void handleFinishFail(String message) {
        // Handle failure in saga
        System.out.println("Saga failed: " + message);
    }
}
