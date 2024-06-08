package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.AlbumRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.PlaylistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TrackRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DataLoadingService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    private static final String CSV_FILE_PATH = "data/spotify_songs.csv";

    public void loadData() throws IOException {
        // Load the CSV file as a resource from the classpath
        Resource resource = new ClassPathResource(CSV_FILE_PATH);

        // Use BufferedReader with InputStreamReader for efficient reading
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            // Maps to store existing albums and artists to avoid duplicates
            Map<String, Album> albumMap = new HashMap<>();
            Map<String, Artist> artistMap = new HashMap<>();
            Map<String, Playlist> playlistMap = new HashMap<>();

            for (CSVRecord csvRecord : csvParser) {
                // Process artist
                String artistName = csvRecord.get("track_artist");
                Artist artist = artistMap.get(artistName);
                if (artist == null) {
                    artist = new Artist();
                    artist.setId(UUID.randomUUID().toString());
                    artist.setName(artistName);
                    artist.setAlbumIds(new ArrayList<>());
                    artist.setTrackIds(new ArrayList<>());
                    artistMap.put(artistName, artist);
                }

                // Process album
                String albumId = csvRecord.get("track_album_id");
                Album album = albumMap.get(albumId);
                if (album == null) {
                    album = new Album();
                    album.setId(albumId);
                    album.setName(csvRecord.get("track_album_name"));
                    album.setReleaseDate(csvRecord.get("track_album_release_date"));
                    album.setArtistId(artist.getId());
                    album.setTrackIds(new ArrayList<>());
                    albumMap.put(albumId, album);
                    artist.getAlbumIds().add(album.getId());
                }

                // Process playlist
                String playlistId = csvRecord.get("playlist_id");
                Playlist playlist = playlistMap.get(playlistId);
                if (playlist == null) {
                    playlist = new Playlist();
                    playlist.setId(playlistId);
                    playlist.setName(csvRecord.get("playlist_name"));
                    playlist.setGenre(csvRecord.get("playlist_genre"));
                    playlist.setSubgenre(csvRecord.get("playlist_subgenre"));
                    playlist.setTrackIds(new ArrayList<>());
                    playlistMap.put(playlistId, playlist);
                }

                // Create and save track
                Track track = new Track();
                track.setId(csvRecord.get("track_id"));
                track.setName(csvRecord.get("track_name"));
                track.setPopularity(Integer.parseInt(csvRecord.get("track_popularity")));
                track.setDanceability(Double.parseDouble(csvRecord.get("danceability")));
                track.setEnergy(Double.parseDouble(csvRecord.get("energy")));
                track.setKey(Integer.parseInt(csvRecord.get("key")));
                track.setLoudness(Double.parseDouble(csvRecord.get("loudness")));
                track.setMode(Integer.parseInt(csvRecord.get("mode")));
                track.setSpeechiness(Double.parseDouble(csvRecord.get("speechiness")));
                track.setAcousticness(Double.parseDouble(csvRecord.get("acousticness")));
                track.setInstrumentalness(Double.parseDouble(csvRecord.get("instrumentalness")));
                track.setLiveness(Double.parseDouble(csvRecord.get("liveness")));
                track.setValence(Double.parseDouble(csvRecord.get("valence")));
                track.setTempo(Double.parseDouble(csvRecord.get("tempo")));
                track.setDuration_ms(Integer.parseInt(csvRecord.get("duration_ms")));

                // Set relationships
                track.setAlbumId(album.getId());
                track.setArtistId(artist.getId());
                track.setPlaylistId(playlist.getId());
                trackRepository.save(track);

                // Update album, artist, and playlist with track ID
                album.getTrackIds().add(track.getId());
                artist.getTrackIds().add(track.getId());
                playlist.getTrackIds().add(track.getId());

                // Save updated album, artist, and playlist
                albumRepository.save(album);
                artistRepository.save(artist);
                playlistRepository.save(playlist);
            }
        }
    }
}