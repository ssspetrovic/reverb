package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.PlaylistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IPlaylistService;

import java.util.List;
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

    @Override
    public void deleteAllPlaylists(){playlistRepository.deleteAll();}

    public Page<Playlist> findAllPlaylistsPage(int page, int size){return playlistRepository.findAll(PageRequest.of(page, size));}

    public long countAllPlaylists(){return playlistRepository.count();}

    public Page<Playlist> findPlaylistsByName(String keyword, Pageable page) {
        return playlistRepository.findPlaylistsByNameContaining(keyword, page);
    }
}
