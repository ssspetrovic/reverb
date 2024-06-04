package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

public class PlaylistGenreCountDTO {
    private String genre;
    private int songCount;

    public PlaylistGenreCountDTO(String genre, int songCount) {
        this.genre = genre;
        this.songCount = songCount;
    }

    // Getteri i setteri
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }
}
