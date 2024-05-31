package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;

import java.util.List;

public interface IPlaylistService {
    Playlist createPlaylist(Playlist playlist);
    Playlist updatePlaylist(String playlistId, Playlist playlist);
    void deletePlaylist(Long playlistId);
    Playlist getPlaylistById(Long playlistId);
    List<Playlist> getAllPlaylists();
    List<Playlist> getPlaylistsByGenre(String genre);
    List<Playlist> getPlaylistsBySubgenre(String subgenre);
}
