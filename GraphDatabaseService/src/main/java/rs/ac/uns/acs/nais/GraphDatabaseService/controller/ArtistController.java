package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.ArtistService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.createArtist(artist), HttpStatus.CREATED);
    }

    @PutMapping("/{artistId}")
    public ResponseEntity<Artist> updateArtist(@PathVariable String artistId, @RequestBody Artist artist) {
        return new ResponseEntity<>(artistService.updateArtist(artistId, artist), HttpStatus.OK);
    }

    @DeleteMapping("/{artistId}")
    public ResponseEntity<Void> deleteArtist(@PathVariable String artistId) {
        artistService.deleteArtist(artistId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable String artistId) {
        Artist artist = artistService.getArtistById(artistId);
        return artist != null ? ResponseEntity.ok(artist) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/popularity/{popularity}")
    public ResponseEntity<List<Artist>> searchArtistsByPopularity(@PathVariable double popularity) {
        return ResponseEntity.ok(artistService.searchArtistsByPopularity(popularity));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Artist>> searchArtistsByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(artistService.searchArtistsByGenre(genre));
    }
}
