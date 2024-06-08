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
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TrackRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.UUID;

@Service
public class DataLoadingService {

    @Autowired
    private TrackRepository trackRepository;

    private static final String CSV_FILE_PATH = "data/spotify_songs.csv";

    public void loadData() throws IOException {
        // Load the CSV file as a resource from the classpath
        Resource resource = new ClassPathResource(CSV_FILE_PATH);

        // Use BufferedReader with InputStreamReader for efficient reading
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord csvRecord : csvParser) {
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

                // Set album and artist details
                Album album = new Album();
                album.setId(UUID.randomUUID().toString());
                album.setName(csvRecord.get("track_album_name"));
                album.setReleaseDate(csvRecord.get("track_album_release_date"));
                track.setAlbumId(album.getId());

                Artist artist = new Artist();
                artist.setId(UUID.randomUUID().toString()); // generate a new UUID for the artist ID
                artist.setName(csvRecord.get("track_artist"));
                track.setArtistId(artist.getId());

                // Save the track
                trackRepository.save(track);
            }
        }
    }
}
