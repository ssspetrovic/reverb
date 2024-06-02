package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;

import java.util.List;

public interface IArtistService {
    Artist createArtist(Artist artist);
    Artist updateArtist(Long artist_id, Artist artist);
    void deleteArtist(Long artist_id);
    Artist getArtistById(Long artist_id);
    Artist getArtistByName(String name);
    List<Artist> getAllArtists();
    List<Artist> searchArtistsByPopularity(String popularity);
    List<Artist> searchArtistsByGenre(String genre);
}
