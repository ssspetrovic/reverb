package rs.ac.uns.acs.nais.orchestratorservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.orchestratorservice.model.UserTransactionRequest;

@Service
public class OrchestratorService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public OrchestratorService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void addSongToFavorites(Long userId, Long songId) {
        // Perform transaction orchestration logic here
        UserTransactionRequest request = new UserTransactionRequest();
        request.setUserId(userId);
        request.setSongId(songId);
        kafkaTemplate.send("add-song-to-favorites-topic", request);
    }
}
