package rs.ac.uns.acs.nais.OrchestratorService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import rs.ac.uns.acs.nais.OrchestratorService.dto.TrackDTO;

@Service
public class OrchestratorService {

    private final KafkaTemplate<String, TrackDTO> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrchestratorService(KafkaTemplate<String, TrackDTO> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.kafka.topics.start-saga}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleStartSaga(String message) {
        try {
            // Deserialize the message
            TrackDTO trackDTO = objectMapper.readValue(message, TrackDTO.class);

            // Notify relational service
            kafkaTemplate.send("relational-service-topic", trackDTO);

            // Notify elastic-search service
            kafkaTemplate.send("elastic-search-service-topic", trackDTO);

        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            // Handle error (e.g., send to a failure topic or perform compensating transactions)
        }
    }

    @KafkaListener(topics = "${spring.kafka.topics.finish-success}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleFinishSuccess(String message) {
        System.out.println("Transaction successful: " + message);
    }

    @KafkaListener(topics = "${spring.kafka.topics.finish-fail}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleFinishFail(String message) {
        System.out.println("Transaction failed: " + message);
        // Handle rollback or compensating transactions
    }
}
