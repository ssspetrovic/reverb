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

    public Artist updateArtist(String artistId, Artist artist) {
        return artistRepository.updateArtist(artistId, artist);
    }

    public void deleteArtist(String artistId) {
        artistRepository.deleteArtist(artistId);
    }

    public Artist getArtistById(String artistId) {
        return artistRepository.getArtistById(artistId);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.getAllArtists();
    }

    // Pretraga izvođača po popularnosti
    public List<Artist> searchArtistsByPopularity(double popularity) {
        return artistRepository.searchArtistsByPopularity(popularity);
    }

    // Pretraga izvođača po žanru
    public List<Artist> searchArtistsByGenre(String genre) {
        return artistRepository.searchArtistsByGenre(genre);
    }
}
