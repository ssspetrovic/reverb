package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dtos;

import lombok.Getter;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.util.List;

@Getter
public class TracksTempoAggDTO {
    List<Track> tracks;
    double avgTempo;

    public TracksTempoAggDTO(List<Track> tracks, double tempo) {
        this.tracks = tracks;
        this.avgTempo = tempo;
    }
}
