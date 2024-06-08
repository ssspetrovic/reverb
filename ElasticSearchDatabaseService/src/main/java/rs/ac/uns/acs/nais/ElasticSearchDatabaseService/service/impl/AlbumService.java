package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.AlbumRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IAlbumService;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IArtistService;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

//    @Override
//    public List<Album> findByName(String name) {
//        return albumRepository.findByName(name);
//    }
//
//    @Override
//    public List<Album> findByNameContaining(String name) {
//        return albumRepository.findByNameContaining(name);
//    }
//
//    @Override
//    public List<Album> findByArtistName(String artistName) {
//        return albumRepository.findByArtistName(artistName);
//    }
//
//    @Override
//    public List<Album> findByCustomQuery(String query) {
//        return albumRepository.findByCustomQuery(query);
//    }
//
//    @Override
//    public List<Album> searchByNameOrArtistFuzzy(String searchTerm) {
//        return albumRepository.searchByNameOrArtistFuzzy(searchTerm);
//    }
    @Override
    public void deleteAllAlbums(){albumRepository.deleteAll();}
}
