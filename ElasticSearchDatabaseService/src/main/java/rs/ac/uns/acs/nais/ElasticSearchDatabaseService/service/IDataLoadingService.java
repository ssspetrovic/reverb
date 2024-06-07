package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import java.io.IOException;

public interface IDataLoadingService {
    void loadArtists() throws IOException;
    void loadAlbums() throws IOException;
}
