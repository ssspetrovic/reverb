package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MostPopularArtistsDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.ArtistsWithLargestAveragePopularityDTO;

import java.util.List;

public interface IArtistService {
    Artist createArtist(Artist artist);
    Artist updateArtist(Artist artist);
    void deleteArtist(String name);
    Artist getArtistByName(String name);
    List<Artist> getAllArtists();
    List<Artist> searchArtistsByPopularity(String popularity);
    List<Artist> searchArtistsByGenre(String genre);
    List<MostPopularArtistsDTO> getArtistsSortedByAveragePopularity();
    List<ArtistsWithLargestAveragePopularityDTO> getArtistsWithLargestAveragePopularity();
}
