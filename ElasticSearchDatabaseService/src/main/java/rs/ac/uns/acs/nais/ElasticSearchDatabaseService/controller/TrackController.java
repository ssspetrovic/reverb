package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;


import com.lowagie.text.DocumentException;
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
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITrackService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private ITrackService trackService;

    @PostMapping
    public Track saveTrack(@RequestBody Track track){
        return trackService.saveTrack(track);
    }

    @GetMapping("/{id}")
    public Optional<Track> findTrackById(@PathVariable String id){
        return trackService.findTrackById(id);
    }

    @GetMapping("/page")
    public Page<Track> findAllTracksPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue = "1000") int size){
        return trackService.findAllTracksPage(page, size);
    }

    @GetMapping
    public Iterable<Track> findAllTracks(){return trackService.findAllTracks();}

    @DeleteMapping("/{id}")
    public void deleteTrackById(@PathVariable String id){
        trackService.deleteTrackById(id);
    }

    @DeleteMapping
    public void deleteAllTracks(){trackService.deleteAllTracks();}
    @GetMapping("/count")
    public long countAllTracks(){return trackService.countAllTracks();}

    @GetMapping("/byArtistAndEnergy")
    public List<Track> getTracksByArtistIdAndEnergyRangeWithAvgTempo(
            @RequestParam String artistId,
            @RequestParam double minEnergy,
            @RequestParam double maxEnergy) {
        return trackService.findTracksByArtistIdAndEnergyRangeWithAvgTempoAggregation(artistId, minEnergy, maxEnergy);
    }


    @GetMapping("/search/tracksByTempoAndDuration")
    public List<Track> searchTracksByArtistIdTempoAndDuration(
            @RequestParam double minTempo,
            @RequestParam int maxDuration,
            @RequestParam int page,
            @RequestParam int size) {
        return trackService.searchTracksByTempoAndDuration(minTempo, maxDuration, page, size);
    }

    @GetMapping("/byPlaylist/{playlistId}")
    public List<Track> getTracksInPlaylistWithAvgPopularity(
            @PathVariable String playlistId,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return trackService.findTracksInPlaylistWithAvgPopularityAggregation(playlistId, pageable);
    }

    @GetMapping("/byArtistAndLiveness/{artistId}")
    public List<Track> getTracksByArtistIdAndLiveness(@PathVariable String artistId, @RequestParam Double minLiveness){
        Sort sort = Sort.by(Sort.Direction.DESC, "popularity");
        Pageable pageable = PageRequest.of(0, 500, sort);
        Page<Track> trackPage = trackService.findTracksByArtistAndLiveness(artistId, minLiveness, pageable);
        return trackPage.getContent();
    }

    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf() {
        Sort sort = Sort.by(Sort.Direction.DESC, "popularity");
        Pageable pageable = PageRequest.of(0, 500, sort);
        Page<Track> trackPage = trackService.findTracksByArtistAndLiveness("537f3cb2-edf2-4a9c-8d25-16c33dc3971e", 0.1, pageable);
        List<Track> tracks = trackPage.getContent();

        try {
            byte[] pdfContents = trackService.export(tracks);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "tracks_report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContents);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
