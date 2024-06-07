package rs.ac.uns.acs.nais.GatewayService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/saga")
public class SagaController {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public SagaController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/verify/{userId}/{trackId}")
    public Mono<ResponseEntity<String>> verifyUserAndTrack(@PathVariable Long userId, @PathVariable String trackId) {
        WebClient webClient = webClientBuilder.build();

        Mono<Boolean> emailConfirmed = webClient.get()
                .uri("http://relational-database-service/users/{id}/email-confirmed", userId)
                .retrieve()
                .bodyToMono(Boolean.class);

        Mono<Boolean> hasFavoriteSong = webClient.get()
                .uri("http://graph-database-service/users/{userId}/has-favorite-song/{trackId}", userId, trackId)
                .retrieve()
                .bodyToMono(Boolean.class);

        return emailConfirmed.zipWith(hasFavoriteSong)
                .flatMap(result -> {
                    boolean isEmailConfirmed = result.getT1();
                    boolean hasTrack = result.getT2();

                    if (isEmailConfirmed && !hasTrack) {
                        return Mono.just(ResponseEntity.ok("Transaction can be completed"));
                    } else {
                        return Mono.just(ResponseEntity.badRequest().body("Transaction cannot be completed"));
                    }
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error occurred: " + e.getMessage())));
    }
}
