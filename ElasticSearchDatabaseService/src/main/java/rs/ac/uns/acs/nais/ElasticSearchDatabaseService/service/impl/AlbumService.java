package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.elasticsearch.search.aggregations.metrics.InternalHDRPercentiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.AlbumRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IAlbumService;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IArtistService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;
    private SimpleDateFormat sourceDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

    @Override
    public Iterable<Album> getAllAlbums(){return albumRepository.findAll();}

    public Page<Album> findAllAlbumsPage(int page, int size){return albumRepository.findAll(PageRequest.of(page, size));}

    public long countAllAlbums(){return albumRepository.count();}

    public Page<Album> findAlbumsByReleaseDate(String date, Pageable page) {
        return albumRepository.findAlbumsByReleaseDate(date, page);
    }
    @Override
    public Optional<Album> findAlbumById(String id) {
        return albumRepository.findById(id);
    }
    @Override
    public Page<Album> findAlbumsByNameInDateRange(String keyword, String startDate, String endDate, Pageable page){
        return albumRepository.findAlbumsByNameInDateRange(keyword, startDate, endDate, page);
    }

}
