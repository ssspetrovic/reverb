package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/findByName/{name}")
    public List<Album> findByName(@PathVariable String name) {
        return albumService.findByName(name);
    }

    @GetMapping("/findByNameContaining/{name}")
    public List<Album> findByNameContaining(@PathVariable String name) {
        return albumService.findByNameContaining(name);
    }

    @GetMapping("/findByArtistName/{artistName}")
    public List<Album> findByArtistName(@PathVariable String artistName) {
        return albumService.findByArtistName(artistName);
    }

    @GetMapping("/findByCustomQuery")
    public List<Album> findByCustomQuery(@RequestParam String query) {
        return albumService.findByCustomQuery(query);
    }

    @GetMapping("/searchByNameOrArtistFuzzy")
    public List<Album> searchByNameOrArtistFuzzy(@RequestParam String searchTerm) {
        return albumService.searchByNameOrArtistFuzzy(searchTerm);
    }
}
