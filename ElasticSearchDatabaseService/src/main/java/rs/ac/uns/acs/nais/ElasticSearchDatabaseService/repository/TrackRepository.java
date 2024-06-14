package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.util.List;

@Repository
public interface TrackRepository extends ElasticsearchRepository<Track, String> {
    Page<Track> findAll(Pageable pageable);


    @Query("{\"bool\": { \"must\": [{ \"range\": { \"tempo\": { \"gte\": ?0 } } }], \"filter\": [{ \"range\": { \"duration_ms\": { \"lte\": ?1 } } }] }}")
    List<Track> findTracksByTempoAndDuration(double minTempo, int maxDuration, Pageable pageable);

    @Query("{ \"bool\": { \"must\": [ { \"match\": { \"artistId\": \"?0\" } }, { \"range\": { \"liveness\": { \"gte\": ?1 } } } ] } }")
    Page<Track> findTracksByTempoAndArtistId(String artistId, Double minLiveness, Pageable pageable);

}
