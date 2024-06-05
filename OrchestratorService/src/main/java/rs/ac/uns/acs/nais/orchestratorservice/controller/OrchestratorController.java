package rs.ac.uns.acs.nais.orchestratorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.acs.nais.orchestratorservice.model.UserTransactionRequest;
import rs.ac.uns.acs.nais.orchestratorservice.service.OrchestratorService;

@RestController
@RequestMapping("/orchestrate")
public class OrchestratorController {

    private final OrchestratorService orchestratorService;

    @Autowired
    public OrchestratorController(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @PostMapping("/add-song-to-favorites")
    public void addSongToFavorites(@RequestBody UserTransactionRequest request) {
        orchestratorService.addSongToFavorites(request.getUserId(), request.getSongId());
    }
}
