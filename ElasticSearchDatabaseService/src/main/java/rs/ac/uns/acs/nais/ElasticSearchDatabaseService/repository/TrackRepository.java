package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

public interface TrackRepository extends ElasticsearchRepository<Track, String> {
}