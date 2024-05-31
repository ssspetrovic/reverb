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
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist updatePlaylist(String playlistId, Playlist playlist) {
        Playlist existingPlaylist = playlistRepository.findById(Long.parseLong(playlistId))
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        playlist.setId(existingPlaylist.getId());
        return playlistRepository.save(playlist);
    }

    @Override
    public void deletePlaylist(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }

    @Override
    public Playlist getPlaylistById(Long playlistId) {
        return playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
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
