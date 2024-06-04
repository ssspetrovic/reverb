package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaylistGenreCountDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaylistCountAveragePopularityDTO;

import java.util.List;

public interface IPlaylistService {
    Playlist createPlaylist(Playlist playlist);
    Playlist updatePlaylist(String playlist_id, Playlist playlist);
    void deletePlaylist(String playlist_id);
    Playlist getPlaylistById(String playlist_id);
    List<Playlist> getAllPlaylists();
    List<Playlist> getPlaylistsByGenre(String genre);
    List<Playlist> getPlaylistsBySubgenre(String subgenre);
    Playlist updatePlaylistGenreBySong(String playlistName, String trackId, String newGenre);
    List<PlaylistGenreCountDTO> getPlaylistGenreWithSongCount();
    List<PlaylistCountAveragePopularityDTO> getPlaylistCountAndAveragePopularity();
}
