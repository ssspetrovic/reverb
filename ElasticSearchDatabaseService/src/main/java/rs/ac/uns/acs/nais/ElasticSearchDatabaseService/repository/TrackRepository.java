package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

@Repository
public interface TrackRepository extends ElasticsearchRepository<Track, String> {

}
