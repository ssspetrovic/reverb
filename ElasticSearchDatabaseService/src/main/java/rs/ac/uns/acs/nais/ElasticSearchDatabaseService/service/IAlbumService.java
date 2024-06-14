package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import com.lowagie.text.DocumentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    long countAllAlbums();
    Page<Album> findAlbumsByReleaseDate(String date, Pageable pageable);
    Optional<Album> findAlbumById(String date);
    Page<Album> findAlbumsByNameInDateRange(String keyword, String startDate, String endDate, Pageable pageable);
    byte[] export(List<Album> albums) throws IOException, DocumentException;
}
