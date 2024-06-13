package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.util.Date;
import java.util.List;

@Repository
public interface AlbumRepository extends ElasticsearchRepository<Album, String> {
//    List<Album> findByName(String name);
//
//    List<Album> findByNameContaining(String name);
//
//    List<Album> findByArtistName(String artistName);
//
//    @Query("{\"bool\": {\"should\": [{\"match\": {\"name\": \"?0\"}}, {\"match\": {\"artist.artistName\": \"?0\"}}]}}")
//    List<Album> findByCustomQuery(String query);
//
//    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"name^3\",\"artist.artistName\"],\"fuzziness\":\"AUTO\"}}")
//    List<Album> searchByNameOrArtistFuzzy(String searchTerm);
    Page<Album> findAll(Pageable pageable);

    //Ispisi Albume nakon zadatog datuma i sortiraj ih od najnovijeg do najstarijeg
    @Query("{ \"bool\": { \"must\": [ {\"range\": { \"releaseDate\": { \"gte\": \"?0\" } } } ] } }")
    Page<Album> findAlbumsByReleaseDate(String data, Pageable page);

    //Ispisi Albume koji sadrze zadat tekst u imenu, nalaze se u datom rasponu datuma i soritraj ih po ID-u Artista
    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}, {\"range\": {\"releaseDate\": {\"gte\": \"?1\", \"lte\": \"?2\"}}}]}}")
    Page<Album> findAlbumsByNameInDateRange(String keyword, String startDate, String endDate, Pageable page);

}

