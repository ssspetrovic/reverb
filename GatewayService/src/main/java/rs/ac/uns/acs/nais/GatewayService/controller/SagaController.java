package rs.ac.uns.acs.nais.GatewayService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                .uri("http://localhost:9000/relational-database-service/users/{id}/email-confirmed", userId)
                .retrieve()
                .bodyToMono(Boolean.class);

        Mono<Boolean> hasFavoriteSong = webClient.get()
                .uri("http://localhost:9000/graph-database-service/users/{userId}/has-favorite-song/{trackId}", userId, trackId)
                .retrieve()
                .bodyToMono(Boolean.class);

        return emailConfirmed.zipWith(hasFavoriteSong)
                .flatMap(result -> {
                    boolean isEmailConfirmed = result.getT1();
                    boolean hasTrack = result.getT2();

                    if (isEmailConfirmed && !hasTrack) {
                        // Perform transaction (add song to favorite songs)
                        return addSongToFavorite(userId, trackId);
                    } else {
                        // Transaction cannot be completed
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Transaction cannot be completed"));
                    }
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error occurred: " + e.getMessage())));
    }

    private Mono<ResponseEntity<String>> addSongToFavorite(Long userId, String trackId) {
        WebClient webClient = webClientBuilder.build();
        return webClient.post()
                .uri("http://localhost:9000/graph-database-service/users/{userId}/favorite-songs", userId)
                .bodyValue("{\"trackId\": \"" + trackId + "\"}")
                .retrieve()
                .toEntity(String.class)
                .map(response -> ResponseEntity.ok("Transaction completed successfully: " + response.getBody()));
    }
}
