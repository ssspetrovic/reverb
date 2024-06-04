package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

public class PlaylistCountAveragePopularityDTO {
    private String name;
    private int numSongs;
    private double avgPopularity;

    public PlaylistCountAveragePopularityDTO(String name, int numSongs, double avgPopularity) {
        this.name = name;
        this.numSongs = numSongs;
        this.avgPopularity = avgPopularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public void setNumSongs(int numSongs) {
        this.numSongs = numSongs;
    }

    public double getAvgPopularity() {
        return avgPopularity;
    }

    public void setAvgPopularity(double avgPopularity) {
        this.avgPopularity = avgPopularity;
    }
}
