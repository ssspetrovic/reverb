package rs.ac.uns.acs.nais.orchestratorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.acs.nais.orchestratorservice.service.OrchestratorService;

@RestController
@RequestMapping("/orchestrator")
public class OrchestratorController {

    private final OrchestratorService orchestratorService;

    @Autowired
    public OrchestratorController(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @PostMapping("/start")
    public void startSaga(@RequestBody StartSagaRequest request) {
        orchestratorService.startSaga(request.getUserId(), request.getSongId());
    }

    public static class StartSagaRequest {
        private String userId;
        private String songId;

        // Getters and setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getSongId() {
            return songId;
        }

        public void setSongId(String songId) {
            this.songId = songId;
        }
    }
}
