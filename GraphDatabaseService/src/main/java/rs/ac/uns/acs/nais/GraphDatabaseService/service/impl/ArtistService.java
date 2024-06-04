package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IArtistService;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MostPopularArtistsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.ArtistsWithLargestAveragePopularityDTO;

import java.util.List;

@Service
public class ArtistService implements IArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.createArtist(artist.getName());
    }

    public Artist updateArtist(Artist artist) {
        return artistRepository.updateArtist(artist.getName());
    }

    public void deleteArtist(String name) {
        artistRepository.deleteArtist(name);
    }

    public Artist getArtistByName(String name) {
        return artistRepository.getArtistByName(name);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.getAllArtists();
    }

    public List<Artist> searchArtistsByPopularity(String popularity) {
        return artistRepository.searchArtistsByPopularity(popularity);
    }

    public List<Artist> searchArtistsByGenre(String genre) {
        return artistRepository.searchArtistsByGenre(genre);
    }

    public List<MostPopularArtistsDTO> getArtistsSortedByAveragePopularity() {
        return artistRepository.getArtistsSortedByAveragePopularity();
    }

    public List<ArtistsWithLargestAveragePopularityDTO> getArtistsWithLargestAveragePopularity() {
        return artistRepository.getArtistsWithLargestAveragePopularity();
    }
}
