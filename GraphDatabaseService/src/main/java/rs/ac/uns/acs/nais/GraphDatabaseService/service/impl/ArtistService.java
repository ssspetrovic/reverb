package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IArtistService;

import java.util.List;

@Service
public class ArtistService implements IArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.createArtist(artist);
    }

    public Artist updateArtist(Long artist_id, Artist artist) {
        artist.setArtistId(artist_id);
        return artistRepository.updateArtist(artist);
    }

    public void deleteArtist(Long artist_id) {
        artistRepository.deleteArtist(artist_id);
    }

    public Artist getArtistById(Long artist_id) {
        return artistRepository.getArtistById(artist_id);
    }

    public Artist getArtistByName(String name) {
        return artistRepository.getArtistByName(name);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.getAllArtists();
    }

    // Pretraga izvođača po popularnosti
    public List<Artist> searchArtistsByPopularity(String popularity) {
        return artistRepository.searchArtistsByPopularity(popularity);
    }

    // Pretraga izvođača po žanru
    public List<Artist> searchArtistsByGenre(String genre) {
        return artistRepository.searchArtistsByGenre(genre);
    }
}
