package rs.ac.uns.acs.nais.orchestratorservice.model;

public class UserTransactionRequest {

    private Long userId;
    private Long songId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
