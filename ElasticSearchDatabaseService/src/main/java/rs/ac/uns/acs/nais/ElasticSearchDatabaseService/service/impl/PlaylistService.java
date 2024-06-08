package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.PlaylistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IPlaylistService;

import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Optional<Playlist> findPlaylistById(String id) {
        return playlistRepository.findById(id);
    }

    @Override
    public Iterable<Playlist> findAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public void deletePlaylistById(String id) {
        playlistRepository.deleteById(id);
    }
}