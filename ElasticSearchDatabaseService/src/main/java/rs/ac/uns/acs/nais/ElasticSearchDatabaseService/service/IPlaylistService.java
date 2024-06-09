package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;

import java.util.Optional;

public interface IPlaylistService {
    Playlist savePlaylist(Playlist playlist);
    Optional<Playlist> findPlaylistById(String id);
    Iterable<Playlist> findAllPlaylists();
    void deletePlaylistById(String id);
    void deleteAllPlaylists();
    Page<Playlist> findAllPlaylistsPage(int page, int size);
    long countAllPlaylists();

}
