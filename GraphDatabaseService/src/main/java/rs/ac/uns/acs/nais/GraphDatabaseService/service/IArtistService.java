package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;

import java.util.List;

public interface IArtistService {
    Artist createArtist(Artist artist);
    Artist updateArtist(String artistId, Artist artist);
    void deleteArtist(String artistId);
    Artist getArtistById(String artistId);
    List<Artist> getAllArtists();
    List<Artist> searchArtistsByPopularity(double popularity);
    List<Artist> searchArtistsByGenre(String genre);
}
