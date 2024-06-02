package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ISongService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final ISongService songService;

    @Autowired
    public SongController(ISongService songService) {
        this.songService = songService;
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
}
