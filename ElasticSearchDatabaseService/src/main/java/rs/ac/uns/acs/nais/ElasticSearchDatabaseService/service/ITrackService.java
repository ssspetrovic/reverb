package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.util.List;
import java.util.Optional;

public interface ITrackService {
    Track saveTrack(Track track);
    Optional<Track> findTrackById(String id);
    Iterable<Track> findAllTracks();
    Page<Track> findAllTracksPage(int page, int size);
    void deleteTrackById(String id);
    void deleteAllTracks();
    long countAllTracks();
    List<Track> searchTracksByTempoAndDuration(double minTempo, int maxDuration, int page, int size);
    List<Track> findTracksByArtistIdAndEnergyRangeWithAvgTempoAggregation(String artistId, double minEnergy, double maxEnergy);
    List<Track> findTracksInPlaylistWithAvgPopularityAggregation(String playlistId, Pageable pageable);
}
