package rs.ac.uns.acs.nais.GraphDatabaseService.dto;

public class ArtistsWithLargestAveragePopularityDTO {
    private String name;
    private double avgPopularity;

    public ArtistsWithLargestAveragePopularityDTO(String name, double avgPopularity) {
        this.name = name;
        this.avgPopularity = avgPopularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgPopularity() {
        return avgPopularity;
    }

    public void setAvgPopularity(double avgPopularity) {
        this.avgPopularity = avgPopularity;
    }
}
