package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;

public interface PlaylistRepository extends ElasticsearchRepository<Playlist, String> {
}