package rs.ac.uns.acs.nais.OrchestratorService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.acs.nais.OrchestratorService.service.OrchestratorService;

@RestController
@RequestMapping("/api/orchestrator")
public class OrchestratorController {

    private final OrchestratorService orchestratorService;

    @Autowired
    public OrchestratorController(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

//    @PostMapping("/start-saga")
//    public ResponseEntity<String> startSaga(@RequestBody String jsonString) {
//        orchestratorService.handleStartSaga(jsonString);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Saga started successfully");
//    }
}
