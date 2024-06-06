package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

import java.util.List;

public class Top3SubgenresPerGenreDTO {
    private String genre;
    private List<String> topSubgenres;

    Top3SubgenresPerGenreDTO() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getTopSubgenres() {
        return topSubgenres;
    }

    public void setTopSubgenres(List<String> topSubgenres) {
        this.topSubgenres = topSubgenres;
    }
}