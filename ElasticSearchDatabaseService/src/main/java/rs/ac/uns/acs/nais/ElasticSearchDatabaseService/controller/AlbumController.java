package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @DeleteMapping
    public void deleteAllAlbums(){albumService.deleteAllAlbums();}

    @GetMapping
    public Iterable<Album> getAllAlbums(){return albumService.getAllAlbums();}

    @GetMapping("/page")
    public Page<Album> findAllTracksPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue = "1000") int size){
        return albumService.findAllAlbumsPage(page, size);
    }

    @GetMapping("/searchAlbums")
    public List<Album> searchAlbums(
            @RequestParam String keyword,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return albumService.searchAlbumsByNameAndReleaseDate(keyword, startDate, endDate);
    }
}
