package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.annotations.Query;
import java.util.List;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;

@Repository
public interface ArtistRepository extends ElasticsearchRepository<Artist, String> {

//    List<Artist> findByArtistName(String artistName);
//
//    List<Artist> findByArtistNameContaining(String name);
//
//    @Query("{\"bool\": {\"should\": [{\"match\": {\"artistName\": \"?0\"}}]}}")
//    List<Artist> findByCustomQuery(String query);
//
//    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"artistName^3\"],\"fuzziness\":\"AUTO\"}}")
//    List<Artist> searchByNameFuzzy(String searchTerm);
}