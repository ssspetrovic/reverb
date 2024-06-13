package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IPlaylistService;

import java.util.List;
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

    @DeleteMapping
    public void deleteAllPlaylists() {playlistService.deleteAllPlaylists();}

    @GetMapping("/page")
    public Page<Playlist> findAllPlaylistsPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue = "1000") int size){
        return playlistService.findAllPlaylistsPage(page, size);
    }
    @GetMapping("/count")
    public long countAllPlaylists(){return playlistService.countAllPlaylists();}

    @GetMapping("/search")
    public List<Playlist> findPlaylistByName(@RequestParam String name){
        Sort sort = Sort.by(Sort.Direction.ASC, "genre");
        Pageable pageable = PageRequest.of(0, 500, sort);
        Page<Playlist> playlistsPage = playlistService.findPlaylistsByName(name, pageable);
        return playlistsPage.getContent();
    }
}
