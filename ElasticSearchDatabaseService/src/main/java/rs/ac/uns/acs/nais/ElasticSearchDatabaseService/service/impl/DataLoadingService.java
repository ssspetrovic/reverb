package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.AlbumRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IDataLoadingService;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.UUID;

@Service
public class DataLoadingService implements IDataLoadingService {
    private static final String CSV_FILE_PATH = "spotify_songs.csv";

    private final ArtistRepository artistRepository;

    private final AlbumRepository albumRepository;

    public DataLoadingService(ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    public void loadArtists() throws IOException {
        Reader reader = new FileReader(CSV_FILE_PATH);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        for (CSVRecord csvRecord : csvParser) {
            Artist artist = new Artist();
            artist.setId(UUID.randomUUID().toString());
            artist.setName(csvRecord.get("track_artist"));
            artistRepository.save(artist);
        }
        csvParser.close();
    }

    public void loadAlbums() throws IOException {
        Reader reader = new FileReader(CSV_FILE_PATH);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        for (CSVRecord csvRecord : csvParser) {
            Album album = new Album();
            album.setId(UUID.randomUUID().toString());
            album.setName(csvRecord.get("track_album_name"));
            album.setReleaseDate(csvRecord.get("track_album_release_date"));
            albumRepository.save(album);
        }
        csvParser.close();
    }
}
