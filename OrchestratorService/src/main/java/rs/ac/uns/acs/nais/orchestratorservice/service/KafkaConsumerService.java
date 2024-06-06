package rs.ac.uns.acs.nais.orchestratorservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "transaction-success-topic")
    public void handleTransactionSuccess(String message) {
        // Handle successful transaction
        System.out.println("Transaction successful: " + message);
    }

    @KafkaListener(topics = "transaction-failed-topic")
    public void handleTransactionFailed(String message) {
        // Handle failed transaction
        System.out.println("Transaction failed: " + message);
    }
}
