package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IArtistService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final IArtistService artistService;

    @Autowired
    public ArtistController(IArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.createArtist(artist), HttpStatus.CREATED);
    }

    @PutMapping("/{artist_id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long artist_id, @RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.updateArtist(artist_id, artist), HttpStatus.OK);
    }

    @DeleteMapping("/{artist_id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long artist_id) {
        artistService.deleteArtist(artist_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{artist_id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artist_id) {
        Artist artist = artistService.getArtistById(artist_id);
        return artist != null ? ResponseEntity.ok(artist) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Artist> getArtistByName(@PathVariable String name) {
        Artist artist = artistService.getArtistByName(name);
        return artist != null ? ResponseEntity.ok(artist) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/popularity/{popularity}")
    public ResponseEntity<List<Artist>> searchArtistsByPopularity(@PathVariable String popularity) {
        return ResponseEntity.ok(artistService.searchArtistsByPopularity(popularity));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Artist>> searchArtistsByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(artistService.searchArtistsByGenre(genre));
    }
}
