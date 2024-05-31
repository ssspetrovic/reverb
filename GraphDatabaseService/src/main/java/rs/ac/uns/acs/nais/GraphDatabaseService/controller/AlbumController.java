package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.AlbumService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Album;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return new ResponseEntity<>(albumService.createAlbum(album), HttpStatus.CREATED);
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<Album> updateAlbum(@PathVariable String albumId, @RequestBody Album album) {
        return new ResponseEntity<>(albumService.updateAlbum(albumId, album), HttpStatus.OK);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String albumId) {
        albumService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Album> getAlbumById(@PathVariable String albumId) {
        Album album = albumService.getAlbumById(albumId);
        return album != null ? ResponseEntity.ok(album) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/popularity/{popularity}")
    public ResponseEntity<List<Album>> searchAlbumsByPopularity(@PathVariable double popularity) {
        return ResponseEntity.ok(albumService.searchAlbumsByPopularity(popularity));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Album>> searchAlbumsByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(albumService.searchAlbumsByGenre(genre));
    }
}
