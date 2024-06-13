package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import java.util.List;

@Repository
public interface PlaylistRepository extends ElasticsearchRepository<Playlist, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"playlist_name\": \"?0\"}}], \"filter\": [{\"range\": {\"num_tracks\": {\"gte\": ?1}}}]}, \"sort\": [{\"creation_date\": {\"order\": \"desc\"}}]}")
    List<Playlist> findPlaylistsByNameWithFiltersAndSorting(String text, int minTracks, Pageable pageable);

    Page<Playlist> findAll(Pageable pageable);

}