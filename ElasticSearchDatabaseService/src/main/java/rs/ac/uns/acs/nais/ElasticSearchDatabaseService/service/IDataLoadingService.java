package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.springframework.stereotype.Service;

import java.io.IOException;


public interface IDataLoadingService {
    void loadArtists() throws IOException;
    void loadAlbums() throws IOException;
}
