package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;
import com.lowagie.text.DocumentException;
import com.lowagie.text.DocumentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dtos.TrackDTO;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dtos.TracksPopularityAggDTO;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dtos.TracksTempoAggDTO;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.io.IOException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ITrackService {
    Track saveTrack(Track track);
    Optional<Track> findTrackById(String id);
    Iterable<Track> findAllTracks();
    Page<Track> findAllTracksPage(int page, int size);
    void deleteTrackById(String id);
    void deleteAllTracks();
    List<Track> searchTracksByTempoAndDuration(double minTempo, int maxDuration, int page, int size);
    TracksTempoAggDTO findTracksByArtistIdAndEnergyRangeWithAvgTempoAggregation(String artistId, double minEnergy, double maxEnergy);
    TracksPopularityAggDTO findTracksInPlaylistWithAvgPopularityAggregation(String playlistId, Pageable pageable);
    void handleTrackMessage(TrackDTO trackDTO);
    long countAllTracks();
    Page<Track> findTracksByArtistAndLiveness(String artistId, Double liveness, Pageable pageable);
    public byte[] export(List<Track> tracks) throws IOException, DocumentException;
}
