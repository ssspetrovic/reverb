package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IPlaylistService;

import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;

    @PostMapping
    public Playlist savePlaylist(@RequestBody Playlist playlist){
        return playlistService.savePlaylist(playlist);
    }

    @GetMapping("/{id}")
    public Optional<Playlist> findPlaylistById(@PathVariable String id){
        return playlistService.findPlaylistById(id);
    }

    @GetMapping
    public Iterable<Playlist> findAllPlaylists(){
        return playlistService.findAllPlaylists();
    }

    @DeleteMapping("/{id}")
    public void deletePlaylistById(@PathVariable String id){
        playlistService.deletePlaylistById(id);
    }


}
