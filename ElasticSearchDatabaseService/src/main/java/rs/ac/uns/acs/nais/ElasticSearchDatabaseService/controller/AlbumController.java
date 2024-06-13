package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.AlbumService;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/count")
    public long countAllAlbums(){return albumService.countAllAlbums();}

    @GetMapping("/searchByReleaseDate")
    public List<Album> searchByReleaseDate(@RequestParam String date){
        Sort sort = Sort.by(Sort.Direction.DESC, "releaseDate");
        Pageable pageable = PageRequest.of(0, 500, sort);
        Page<Album> albumsPage = albumService.findAlbumsByReleaseDate(date, pageable);
        return albumsPage.getContent();
    }
    @GetMapping("/{id}")
    public Optional<Album> findAlbumById(@PathVariable String id) {
        return albumService.findAlbumById(id);
    }
    @GetMapping("/searchbyNameInDateRange")
    public List<Album> searchByNameInDateRange(@RequestParam String keyword, @RequestParam String startDate, @RequestParam String endDate){
        Sort sort = Sort.by(Sort.Direction.ASC, "artistId");
        Pageable pageable = PageRequest.of(0, 500, sort);
        Page<Album> albumPage = albumService.findAlbumsByNameInDateRange(keyword, startDate, endDate, pageable);
        return albumPage.getContent();
    }

}
