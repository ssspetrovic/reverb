package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PlaylistRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaylistGenreCountDTO;

import java.util.List;

@Service
public class PlaylistService implements IPlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.createPlaylist(playlist.getPlaylistId(), playlist.getName(), playlist.getGenre(), playlist.getSubgenre());
    }

    @Override
    public Playlist updatePlaylist(String playlist_id, Playlist playlist) {
        playlist.setPlaylistId(playlist_id);
        return playlistRepository.updatePlaylist(playlist.getPlaylistId(), playlist.getName(), playlist.getGenre(), playlist.getSubgenre());
    }

    @Override
    public void deletePlaylist(String playlist_id) {
        playlistRepository.deletePlaylist(playlist_id);
    }

    @Override
    public Playlist getPlaylistById(String playlist_id) {
        return playlistRepository.getPlaylistById(playlist_id);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.getAllPlaylists();
    }

    @Override
    public List<Playlist> getPlaylistsByGenre(String genre) {
        return playlistRepository.getPlaylistsByGenre(genre);
    }

    @Override
    public List<Playlist> getPlaylistsBySubgenre(String subgenre) {
        return playlistRepository.getPlaylistsBySubgenre(subgenre);
    }

    @Override
    public Playlist updatePlaylistGenreBySong(String playlistName, String trackId, String newGenre) {
        return playlistRepository.updatePlaylistGenreBySong(playlistName, trackId, newGenre);
    }

    @Override
    public List<PlaylistGenreCountDTO> getPlaylistGenreWithSongCount() {
        return playlistRepository.getPlaylistGenreWithSongCount();
    }

}
