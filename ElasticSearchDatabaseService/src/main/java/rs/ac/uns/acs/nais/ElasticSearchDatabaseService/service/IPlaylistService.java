package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;

import java.util.Optional;

public interface IPlaylistService {
    Playlist savePlaylist(Playlist playlist);
    Optional<Playlist> findPlaylistById(String id);
    Iterable<Playlist> findAllPlaylists();
    void deletePlaylistById(String id);
}
