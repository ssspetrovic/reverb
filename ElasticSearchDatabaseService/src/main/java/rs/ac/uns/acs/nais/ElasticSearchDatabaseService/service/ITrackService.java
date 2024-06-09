package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.util.Optional;

public interface ITrackService {
    Track saveTrack(Track track);
    Optional<Track> findTrackById(String id);
    Iterable<Track> findAllTracks();
    Page<Track> findAllTracksPage(int page, int size);
    void deleteTrackById(String id);
    void deleteAllTracks();
    long countAllTracks();
}
