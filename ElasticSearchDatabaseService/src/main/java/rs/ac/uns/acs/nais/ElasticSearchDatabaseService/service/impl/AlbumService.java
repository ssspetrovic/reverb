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

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

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
