package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

public class Top4SubgenresPerGenreDTO {
    private String genre;
    private String subgenre;

    Top4SubgenresPerGenreDTO() {
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }
}