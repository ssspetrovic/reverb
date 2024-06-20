package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dtos;

import lombok.Getter;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.util.List;

@Getter
public class TracksPopularityAggDTO {

    List<Track> tracks;
    double avgPopularity;

    public TracksPopularityAggDTO(List<Track> tracks, double avgPopularity) {
        this.tracks = tracks;
        this.avgPopularity = avgPopularity;
    }
}
