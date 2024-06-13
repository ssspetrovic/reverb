package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.AlbumService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

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

    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf() {

        List<Album> albums = albumService.findAlbumsByNameInDateRange("close", "2015-01-01", "2020-12-31", PageRequest.of(0, 500)).getContent();

        try {
            byte[] pdfContents = albumService.export(albums);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "albums_report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContents);
        } catch (IOException | DocumentException e) {
            e.printStackTrace(); // Log the exception details
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
