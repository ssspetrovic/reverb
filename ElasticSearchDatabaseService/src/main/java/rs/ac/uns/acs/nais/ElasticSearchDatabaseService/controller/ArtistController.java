package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/search/custom")
    public List<Artist> findByCustomQuery(@RequestParam String query) {
        return artistService.findByCustomQuery(query);
    }
/* 
    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf() {
        List<Product> products = productService.findByCustomQuery("brown shorts"); 
        try {
            byte[] pdfContents = productService.export(products);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "products.pdf");

            return ResponseEntity.ok()
                                 .headers(headers)
                                 .body(pdfContents);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
*/
}