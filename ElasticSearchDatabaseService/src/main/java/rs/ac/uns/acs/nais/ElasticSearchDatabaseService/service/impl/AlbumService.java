package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import co.elastic.clients.json.JsonData;
import co.elastic.clients.util.DateTime;
import org.elasticsearch.search.aggregations.metrics.InternalHDRPercentiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Artist;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.AlbumRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ArtistRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IAlbumService;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IArtistService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ElasticsearchOperations elasticsearchOperations) {

        this.albumRepository = albumRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public void deleteAllAlbums(){albumRepository.deleteAll();}

    @Override
    public Iterable<Album> getAllAlbums(){return albumRepository.findAll();}

    public Page<Album> findAllAlbumsPage(int page, int size){return albumRepository.findAll(PageRequest.of(page, size));}

    public List<Album> searchAlbumsByNameAndReleaseDate(String keyword, String startDate, String endDate) {
        // Build the query
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> b
                                .must(
                                        m -> m.match(t -> t.field("name").query(keyword))
                                )
                                .must(m -> m
                                        .range(r -> r.field("releaseDate")
                                                .gte(JsonData.of(DateTime.of(startDate)))
                                                .lte(JsonData.of(DateTime.of(endDate)))
                                        )
                                )
                        )
                )
                .build();

        // Execute the query
        SearchHits<Album> searchHits = elasticsearchOperations.search(query, Album.class);

        // Collect and return the results
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
