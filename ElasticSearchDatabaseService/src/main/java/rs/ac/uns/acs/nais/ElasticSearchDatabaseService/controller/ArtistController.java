package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.ArtistService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    public Artist saveArtist(@RequestBody Artist artist) {
        return artistService.saveArtist(artist);
    }

    // Retrieve an artist by ID
    @GetMapping("/{id}")
    public Optional<Artist> findArtistById(@PathVariable String id) {
        return artistService.findArtistById(id);
    }

    // Retrieve all artists
    @GetMapping
    public Iterable<Artist> findAllArtists() {
        return artistService.findAllArtists();
    }

    // Delete an artist by ID
    @DeleteMapping("/{id}")
    public void deleteArtistById(@PathVariable String id) {
        artistService.deleteArtistById(id);
    }

    @DeleteMapping
    public void deleteAllArtists(){artistService.deleteAllArtists();}

    @GetMapping("/page")
    public Page<Artist> findAllArtistsPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue = "1000") int size){
        return artistService.findAllArtistsPage(page, size);
    }

    @GetMapping("/count")
    public long countAllArtists(){return artistService.countAllArtists();}

//    @GetMapping("/search/custom")
//    public List<Artist> findByCustomQuery(@RequestParam String query) {
//        return artistService.findByCustomQuery(query);
//    }
}