package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PlaylistRepository;

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
        return playlistRepository.createPlaylist(playlist);
    }

    @Override
    public Playlist updatePlaylist(String playlist_id, Playlist playlist) {
        playlist.setPlaylistId(playlist_id);
        return playlistRepository.updatePlaylist(playlist);
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
}
