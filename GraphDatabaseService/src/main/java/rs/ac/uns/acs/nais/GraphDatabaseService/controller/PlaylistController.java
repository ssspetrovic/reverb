package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPlaylistService;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final IPlaylistService playlistService;

    @Autowired
    public PlaylistController(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        Playlist createdPlaylist = playlistService.createPlaylist(playlist);
        return new ResponseEntity<>(createdPlaylist, HttpStatus.CREATED);
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable String playlistId, @RequestBody Playlist playlist) {
        Playlist updatedPlaylist = playlistService.updatePlaylist(playlistId, playlist);
        return ResponseEntity.ok(updatedPlaylist);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable Long playlistId) {
        Playlist playlist = playlistService.getPlaylistById(playlistId);
        return ResponseEntity.ok(playlist);
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Playlist>> getPlaylistsByGenre(@PathVariable String genre) {
        List<Playlist> playlists = playlistService.getPlaylistsByGenre(genre);
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/subgenre/{subgenre}")
    public ResponseEntity<List<Playlist>> getPlaylistsBySubgenre(@PathVariable String subgenre) {
        List<Playlist> playlists = playlistService.getPlaylistsBySubgenre(subgenre);
        return ResponseEntity.ok(playlists);
    }
}
