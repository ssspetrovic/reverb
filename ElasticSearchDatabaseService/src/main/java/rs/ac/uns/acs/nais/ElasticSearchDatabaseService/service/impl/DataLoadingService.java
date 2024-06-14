package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.utils.DateUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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
    private static final int BATCH_SIZE = 1000;
    private static final SimpleDateFormat CSV_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private static final Logger logger = LoggerFactory.getLogger(DataLoadingService.class);



    public void loadData() throws IOException, InterruptedException, ExecutionException {
        // Load the CSV file as a resource from the classpath
        Resource resource = new ClassPathResource(CSV_FILE_PATH);

        // Use BufferedReader with InputStreamReader for efficient reading
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            // Maps to store existing albums and artists to avoid duplicates
            Map<String, Album> albumMap = new ConcurrentHashMap<>();
            Map<String, Artist> artistMap = new ConcurrentHashMap<>();
            Map<String, Playlist> playlistMap = new ConcurrentHashMap<>();

            // Use a thread pool for parallel processing
            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

            List<Future<Void>> futures = new ArrayList<>();
            List<CSVRecord> recordsBatch = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                recordsBatch.add(csvRecord);

                if (recordsBatch.size() >= BATCH_SIZE) {
                    // Capture the current batch
                    final List<CSVRecord> currentBatch = new ArrayList<>(recordsBatch);
                    futures.add(executorService.submit(() -> {
                        processRecords(currentBatch, artistMap, albumMap, playlistMap);
                        return null;
                    }));
                    recordsBatch.clear();
                }
            }

            // Process the remaining records
            if (!recordsBatch.isEmpty()) {
                // Capture the remaining batch
                final List<CSVRecord> remainingBatch = new ArrayList<>(recordsBatch);
                futures.add(executorService.submit(() -> {
                    processRecords(remainingBatch, artistMap, albumMap, playlistMap);
                    return null;
                }));
            }

            // Wait for all tasks to complete
            for (Future<Void> future : futures) {
                future.get();
            }

            executorService.shutdown();
        }
    }

    private void processRecords(List<CSVRecord> recordsBatch,
                                Map<String, Artist> artistMap,
                                Map<String, Album> albumMap,
                                Map<String, Playlist> playlistMap) {
        List<Track> tracks = new ArrayList<>();
        List<Album> newAlbums = new ArrayList<>();
        List<Artist> newArtists = new ArrayList<>();
        List<Playlist> newPlaylists = new ArrayList<>();

        for (CSVRecord csvRecord : recordsBatch) {
            // Process artist
            String artistName = csvRecord.get("track_artist");
            Artist artist = artistMap.computeIfAbsent(artistName, name -> {
                Artist newArtist = new Artist();
                newArtist.setId(UUID.randomUUID().toString());
                newArtist.setName(name);
                newArtists.add(newArtist);
                return newArtist;
            });

            // Process album
            String albumId = csvRecord.get("track_album_id");
            Album album = albumMap.computeIfAbsent(albumId, id -> {
                Album newAlbum = new Album();
                newAlbum.setId(albumId);
                newAlbum.setName(csvRecord.get("track_album_name"));
                String releaseDateStr = csvRecord.get("track_album_release_date");
                if (DateUtils.isValidDate(releaseDateStr)) {
                    newAlbum.setReleaseDate(releaseDateStr);
                } else {
                    newAlbum.setReleaseDate(null); // or handle it differently if needed
                }
                newAlbum.setArtistId(artist.getId());
                newAlbums.add(newAlbum);
                return newAlbum;
            });

            // Process playlist
            String playlistId = csvRecord.get("playlist_id");
            Playlist playlist = playlistMap.computeIfAbsent(playlistId, id -> {
                Playlist newPlaylist = new Playlist();
                newPlaylist.setId(playlistId);
                newPlaylist.setName(csvRecord.get("playlist_name"));
                newPlaylist.setGenre(csvRecord.get("playlist_genre"));
                newPlaylist.setSubgenre(csvRecord.get("playlist_subgenre"));
                newPlaylists.add(newPlaylist);
                return newPlaylist;
            });

            // Create and add track
            Track track = new Track();
            track.setId(csvRecord.get("track_id"));
            track.setName(csvRecord.get("track_name"));
            track.setPopularity(Integer.parseInt(csvRecord.get("track_popularity")));
            track.setDanceability(parseDouble(csvRecord.get("danceability")));
            track.setEnergy(parseDouble(csvRecord.get("energy")));
            track.setKey(parseInt(csvRecord.get("key")));
            track.setLoudness(parseDouble(csvRecord.get("loudness")));
            track.setMode(parseInt(csvRecord.get("mode")));
            track.setSpeechiness(parseDouble(csvRecord.get("speechiness")));
            track.setAcousticness(parseDouble(csvRecord.get("acousticness")));
            track.setInstrumentalness(parseDouble(csvRecord.get("instrumentalness")));
            track.setLiveness(parseDouble(csvRecord.get("liveness")));
            track.setValence(parseDouble(csvRecord.get("valence")));
            track.setTempo(parseDouble(csvRecord.get("tempo")));
            track.setDuration_ms(parseInt(csvRecord.get("duration_ms")));

            // Set relationships
            track.setAlbumId(album.getId());
            track.setArtistId(artist.getId());
            track.setPlaylistId(playlist.getId());
            tracks.add(track);
        }

        // Batch save entities to the database
        artistRepository.saveAll(newArtists);
        albumRepository.saveAll(newAlbums);
        playlistRepository.saveAll(newPlaylists);
        trackRepository.saveAll(tracks);
    }


    private int parseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            logger.error("Failed to parse int: {}", value, e);
            return 0;
        }
    }

    private double parseDouble(String value) {
        try {
            if (value == null || value.trim().isEmpty()) {
                logger.warn("Empty or null string found for double parsing, defaulting to 0.0");
                return 0.0;
            }
            String cleanedValue = value.trim().replaceAll("[^0-9Ee.-]", "");
            // Handle multiple decimal points
            if (cleanedValue.chars().filter(ch -> ch == '.').count() > 1 && !cleanedValue.contains("E")) {
                throw new NumberFormatException("Multiple decimal points found in: " + value);
            }
            return Double.parseDouble(cleanedValue);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse double: {}", value, e);
            return 0.0;
        }
    }
}
