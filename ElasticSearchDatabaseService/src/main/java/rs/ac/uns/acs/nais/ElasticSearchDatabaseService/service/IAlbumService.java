package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;

import java.util.List;

@Service
public interface IAlbumService {
    List<Album> findByName(String name);

    List<Album> findByNameContaining(String name);

    List<Album> findByArtistName(String artistName);

    List<Album> findByCustomQuery(String query);

    List<Album> searchByNameOrArtistFuzzy(String searchTerm);
}
