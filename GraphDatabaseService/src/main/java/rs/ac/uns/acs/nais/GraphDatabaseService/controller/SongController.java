package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ISongService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPlaylistService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongPopularityProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaylistGenreCountDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MostPopularSongInPlaylistDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongTempoProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.HighEnergyMusicProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.LongestSongInEveryAlbumProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PerformedByProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.IncludedInProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.IncludedInPlaylistProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.report.ReportGenerator;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final ISongService songService;
    private final IPlaylistService playlistService;

    @Autowired
    public SongController(ISongService songService, IPlaylistService playlistService) {
        this.songService = songService;
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        return new ResponseEntity<>(songService.createSong(song), HttpStatus.CREATED);
    }

    @PutMapping("/{trackId}")
    public ResponseEntity<Song> updateSong(@PathVariable String trackId, @RequestBody Song song) {
        return new ResponseEntity<>(songService.updateSong(trackId, song), HttpStatus.OK);
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<Void> deleteSong(@PathVariable String trackId) {
        songService.deleteSong(trackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<?> getSongById(@PathVariable String trackId) {
        try {
            Song song = songService.getSongById(trackId);
            if (song != null) {
                return ResponseEntity.ok(song);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // U slučaju greške, vraćamo odgovor sa statusom 500 i detaljnim opisom greške
            String errorMessage = "Došlo je do greške prilikom dohvatanja pesme sa ID-em: " + trackId + ". Greška: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/artist/{artistName}")
    public ResponseEntity<List<Song>> getSongsByArtist(@PathVariable String artistName) {
        return ResponseEntity.ok(songService.getSongsByArtist(artistName));
    }

    @GetMapping("/album/{albumName}")
    public ResponseEntity<List<Song>> getSongsByAlbum(@PathVariable String albumName) {
        return ResponseEntity.ok(songService.getSongsByAlbum(albumName));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Song>> searchSongsByCriteria(@RequestBody SongSearchCriteriaDTO criteria) {
        return ResponseEntity.ok(songService.searchSongsByCriteria(criteria));
    }

    @GetMapping("/popularity/{popularity}")
    public ResponseEntity<List<Song>> searchSongsByPopularity(@PathVariable Integer popularity) {
        return ResponseEntity.ok(songService.searchSongsByPopularity(popularity));
    }

    @GetMapping("/playlistGenre/{playlistGenre}")
    public ResponseEntity<List<Song>> searchSongsByPlaylistGenre(@PathVariable String playlistGenre) {
        return ResponseEntity.ok(songService.searchSongsByPlaylistGenre(playlistGenre));
    }

    @GetMapping("/energy/{energy}")
    public ResponseEntity<List<Song>> searchSongsByEnergy(@PathVariable String energy) {
        return ResponseEntity.ok(songService.searchSongsByEnergy(energy));
    }

    @GetMapping("/playlistSubgenre/{playlistSubgenre}")
    public ResponseEntity<List<Song>> searchSongsByPlaylistSubgenre(@PathVariable String playlistSubgenre) {
        return ResponseEntity.ok(songService.searchSongsByPlaylistSubgenre(playlistSubgenre));
    }

    @GetMapping("/updatePopularity")
    public ResponseEntity<List<SongPopularityProjection>> updatePopularity() {
        return ResponseEntity.ok(songService.updatePopularityBasedOnEnergy());
    }

    @GetMapping("/updateTempo")
    public ResponseEntity<List<SongTempoProjection>> updateTempoBasedOnPopularity() {
        return ResponseEntity.ok(songService.updateTempoBasedOnPopularity());
    }

    @GetMapping("/mostPopularSongsInPlaylist")
    public ResponseEntity<List<MostPopularSongInPlaylistDTO>> getMostPopularSongsFromEachPlaylist() {
        return ResponseEntity.ok(songService.getMostPopularSongsFromEachPlaylist());
    }

    @GetMapping("/highestEnergy/{playlist_genre}")
    public ResponseEntity<List<HighEnergyMusicProjection>> getHighEnergyMusicBasedOnGenre(@PathVariable String playlist_genre) {
        return ResponseEntity.ok(songService.getHighEnergyMusicBasedOnGenre(playlist_genre));
    }

    @GetMapping("/longestSongPerAlbum")
    public ResponseEntity<List<LongestSongInEveryAlbumProjection>> getLongestSongInEveryAlbum() {
        return ResponseEntity.ok(songService.getLongestSongInEveryAlbum());
    }

    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf(
            @RequestParam(value = "genre") String genre,
            @RequestParam(value = "subgenre") String subgenre,
            @RequestParam(value = "artist") String artist) {

        ReportGenerator reportGenerator = new ReportGenerator(songService, playlistService);
        byte[] pdfContents = reportGenerator.generateReport(genre, subgenre, artist);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContents);
    }

    @PostMapping("{trackId}/performed-by/{name}")
    public ResponseEntity<Void> createPerformedByRelationship(@PathVariable String trackId, @PathVariable String name) {
        songService.createPerformedByRelationship(trackId, name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("{trackId}/included-in/{albumName}")
    public ResponseEntity<Void> createIncludedInRelationship(@PathVariable String trackId, @PathVariable String albumName) {
        songService.createIncludedInRelationship(trackId, albumName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("{trackId}/included-in-playlist/{playlistId}")
    public ResponseEntity<Void> createIncludedInPlaylistRelationship(@PathVariable String trackId, @PathVariable String playlistId) {
        songService.createIncludedInPlaylistRelationship(trackId, playlistId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/performed-by")
    public ResponseEntity<List<PerformedByProjection>> getAllPerformedByRelationships() {
        return ResponseEntity.ok(songService.getAllPerformedByRelationships());
    }

    @GetMapping("/included-in")
    public ResponseEntity<List<IncludedInProjection>> getAllIncludedInRelationships() {
        return ResponseEntity.ok(songService.getAllIncludedInRelationships());
    }

    @GetMapping("/included-in-playlist")
    public ResponseEntity<List<IncludedInPlaylistProjection>> getAllIncludedInPlaylistRelationships() {
        return ResponseEntity.ok(songService.getAllIncludedInPlaylistRelationships());
    }

    @PutMapping("{trackId}/performed-by/{name}")
    public ResponseEntity<Void> updatePerformedByRelationship(@PathVariable String trackId, @PathVariable String name) {
        songService.updatePerformedByRelationship(trackId, name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{trackId}/included-in/{albumName}")
    public ResponseEntity<Void> updateIncludedInRelationship(@PathVariable String trackId, @PathVariable String albumName) {
        songService.updateIncludedInRelationship(trackId, albumName);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{trackId}/included-in-playlist/{playlistId}")
    public ResponseEntity<Void> updateIncludedInPlaylistRelationship(@PathVariable String trackId, @PathVariable String playlistId) {
        songService.updateIncludedInPlaylistRelationship(trackId, playlistId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/performed-by/{trackId}")
    public ResponseEntity<Void> deletePerformedByRelationship(@PathVariable String trackId) {
        songService.deletePerformedByRelationship(trackId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/included-in/{trackId}")
    public ResponseEntity<Void> deleteIncludedInRelationship(@PathVariable String trackId) {
        songService.deleteIncludedInRelationship(trackId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/included-in-playlist/{trackId}")
    public ResponseEntity<Void> deleteIncludedInPlaylistRelationship(@PathVariable String trackId) {
        songService.deleteIncludedInPlaylistRelationship(trackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recommender")
    public ResponseEntity<List<Song>> recommendSongs(@RequestParam String genre, @RequestParam String subgenre, @RequestParam String artist) {
        return ResponseEntity.ok(songService.recommendSongs(genre, subgenre, artist));
    }

    @GetMapping("/top50")
    public ResponseEntity<List<String>> getTop50Songs() {
        return ResponseEntity.ok(songService.getTop50Songs());
    }

}
