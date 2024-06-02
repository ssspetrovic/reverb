package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

public class SongSearchCriteriaDTO {
    private String playlistGenre;
    private String playlistSubgenre;
    private String energy;

    SongSearchCriteriaDTO() {
    }

    public String getPlaylistGenre() {
        return playlistGenre;
    }

    public void setPlaylistGenre(String playlistGenre) {
        this.playlistGenre = playlistGenre;
    }

    public String getPlaylistSubgenre() {
        return playlistSubgenre;
    }

    public void setPlaylistSubgenre(String playlistSubgenre) {
        this.playlistSubgenre = playlistSubgenre;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }
}
