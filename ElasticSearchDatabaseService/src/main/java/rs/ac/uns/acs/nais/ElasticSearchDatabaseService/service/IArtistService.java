package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;

import java.util.List;
import java.util.Optional;

public interface IArtistService {

//    List<Product> findByNameOrDescription(String name, String description);
//
//    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
//
//    List<Product> findByCustomQuery(String query);
//
//    List<Product> searchByDescriptionPhrase(String phrase);
//
//    List<Product> searchByNameOrDescriptionFuzzy(String searchTerm);
//
//    List<Product> findByNameAndDescriptionNotAndOptional(String name, String mustNotTerms, String shouldTerms);
//
//    List<Product> findByFunctionScore(String searchTerm, String boostTerms);
    Artist saveArtist(Artist artist);

    // Retrieve an artist by ID
    Optional<Artist> findArtistById(String id);

    // Retrieve all artists
    Iterable<Artist> findAllArtists();

    // Delete an artist by ID
    void deleteArtistById(String id);

    // Custom query methods
    List<Artist> findByArtistNameOrArtistDescription(String name, String description);

    List<Artist> findByArtistNameContainingOrArtistDescriptionContaining(String name, String description);

    List<Artist> findByCustomQuery(String query);

    List<Artist> searchByDescriptionPhrase(String phrase);

    List<Artist> searchByNameOrDescriptionFuzzy(String searchTerm);

    List<Artist> findByNameAndDescriptionNotAndOptional(String name, String mustNotTerms, String shouldTerms);

    List<Artist> findByNestedAttributeAndAggregate(String attributeName, String attributeValue);

    List<Artist> findByFunctionScore(String searchTerm, String boostTerms);
}
