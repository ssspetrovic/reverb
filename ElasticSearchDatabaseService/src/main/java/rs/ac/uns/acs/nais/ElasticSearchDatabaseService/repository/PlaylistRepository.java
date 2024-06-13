package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;

import java.util.List;

@Repository
public interface PlaylistRepository extends ElasticsearchRepository<Playlist, String> {
    Page<Playlist> findAll(Pageable pageable);

    //Ispisi playliste koje sadrze zadat tekst u imenu i sortiraj ih po zanru
    @Query("{ \"bool\": { \"must\": [ { \"match\": { \"name\": \"?0\" } } ] } }")
    Page<Playlist> findPlaylistsByNameContaining(String keyword, Pageable page);
}