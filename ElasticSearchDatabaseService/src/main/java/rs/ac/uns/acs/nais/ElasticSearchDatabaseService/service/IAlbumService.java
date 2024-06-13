package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;

import java.util.List;

public interface IAlbumService {
//    List<Album> findByName(String name);
//
//    List<Album> findByNameContaining(String name);
//
//    List<Album> findByArtistName(String artistName);
//
//    List<Album> findByCustomQuery(String query);
//
//    List<Album> searchByNameOrArtistFuzzy(String searchTerm);
    void deleteAllAlbums();
    Iterable<Album> getAllAlbums();
    Page<Album> findAllAlbumsPage(int page, int size);
    List<Album> searchAlbumsByNameAndReleaseDate(String keyword, String startDate, String endDate);
}
