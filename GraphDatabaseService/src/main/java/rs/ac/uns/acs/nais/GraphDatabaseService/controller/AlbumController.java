package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IAlbumService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Album;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final IAlbumService albumService;

    @Autowired
    public AlbumController(IAlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return new ResponseEntity<>(albumService.createAlbum(album), HttpStatus.CREATED);
    }

    @PutMapping("/{album_id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable String album_id, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.updateAlbum(album_id, album), HttpStatus.OK);
    }

    @DeleteMapping("/{album_id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String album_id) {
        albumService.deleteAlbum(album_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{album_id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable String album_id) {
        Album album = albumService.getAlbumById(album_id);
        return album != null ? ResponseEntity.ok(album) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/popularity/{popularity}")
    public ResponseEntity<List<Album>> searchAlbumsByPopularity(@PathVariable String popularity) {
        return ResponseEntity.ok(albumService.searchAlbumsByPopularity(popularity));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Album>> searchAlbumsByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(albumService.searchAlbumsByGenre(genre));
    }
}
