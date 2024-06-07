package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;

import java.util.List;

@Repository
public interface AlbumRepository extends ElasticsearchRepository<Album, String> {
    List<Album> findByName(String name);

    List<Album> findByNameContaining(String name);

    List<Album> findByArtistName(String artistName);

    @Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"artist.artistName\": \"?0\"}}]}}")
    List<Album> findByCustomQuery(String query);

    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"name^3\",\"artist.artistName\"],\"fuzziness\":\"AUTO\"}}")
    List<Album> searchByNameOrArtistFuzzy(String searchTerm);
}
