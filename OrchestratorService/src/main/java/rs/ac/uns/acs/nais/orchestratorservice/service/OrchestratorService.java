package rs.ac.uns.acs.nais.orchestratorservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.acs.nais.orchestratorservice.model.UserTransactionRequest;

@Service
public class OrchestratorService {

    private final KafkaTemplate<String, UserTransactionRequest> kafkaTemplate;
    private final RestTemplate restTemplate;

    @Autowired
    public OrchestratorService(KafkaTemplate<String, UserTransactionRequest> kafkaTemplate, RestTemplate restTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate = restTemplate;
    }

    public void addSongToFavorites(Long userId, Long songId) {
        UserTransactionRequest request = new UserTransactionRequest();
        request.setUserId(userId);
        request.setSongId(songId);


        String relationalServiceUrl = "http://relational-database-service";
        Boolean isEmailConfirmed = restTemplate.getForObject(relationalServiceUrl + "/users/" + userId + "/email-confirmed", Boolean.class);
        if (isEmailConfirmed == null || !isEmailConfirmed) {
            kafkaTemplate.send("transaction-failed-topic", request);
            return;
        }

        String graphServiceUrl = "http://graph-database-service";
        Boolean isSongAlreadyFavorite = restTemplate.getForObject(graphServiceUrl + "/users/" + userId + "/favorite-songs/contains/" + songId, Boolean.class);
        if (isSongAlreadyFavorite != null && isSongAlreadyFavorite) {
            kafkaTemplate.send("transaction-failed-topic", request);
            return;
        }

        restTemplate.postForObject(graphServiceUrl + "/users/" + userId + "/favorite-songs/add", songId, Void.class);
        kafkaTemplate.send("transaction-success-topic", request);
    }
}
