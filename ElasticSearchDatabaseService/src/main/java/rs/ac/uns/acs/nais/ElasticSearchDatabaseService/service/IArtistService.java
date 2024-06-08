package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;

import java.util.List;
import java.util.Optional;

public interface IArtistService {
    Artist saveArtist(Artist artist);

    // Retrieve an artist by ID
    Optional<Artist> findArtistById(String id);

    // Retrieve all artists
    Iterable<Artist> findAllArtists();

    // Delete an artist by ID
    void deleteArtistById(String id);

    List<Artist> findByArtistName(String artistName);

    List<Artist> findByArtistNameContaining(String name);

    List<Artist> findByCustomQuery(String query);

    List<Artist> searchByNameFuzzy(String searchTerm);
}
