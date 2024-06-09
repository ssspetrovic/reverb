package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IArtistService;

import java.util.List;
import java.util.Optional;


@Service
public class ArtistService implements IArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    // Retrieve an artist by ID
    public Optional<Artist> findArtistById(String id) {
        return artistRepository.findById(id);
    }

    // Retrieve all artists
    public Iterable<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    // Delete an artist by ID
    public void deleteArtistById(String id) {
        artistRepository.deleteById(id);
    }

    public void deleteAllArtists() {artistRepository.deleteAll();}

    public Page<Artist> findAllArtistsPage(int page, int size){return artistRepository.findAll(PageRequest.of(page, size));}

    public long countAllArtists(){return artistRepository.count();}
}
//    public List<Artist> findByArtistName(String artistName){
//        return artistRepository.findByArtistName(artistName);
//    }
//
//    public List<Artist> findByArtistNameContaining(String name){
//        return artistRepository.findByArtistNameContaining(name);
//    }
//
//    public List<Artist> findByCustomQuery(String query){
//        return artistRepository.findByCustomQuery(query);
//    }
//
//    public List<Artist> searchByNameFuzzy(String searchTerm){
//        return artistRepository.searchByNameFuzzy(searchTerm);
//    }
