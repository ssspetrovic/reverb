package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.AvgAggregate;
import co.elastic.clients.json.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TrackRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITrackService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackService implements ITrackService {

    @Autowired
    private TrackRepository trackRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public TrackService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public Track saveTrack(Track track){
        return trackRepository.save(track);
    }

    @Override
    public Optional<Track> findTrackById(String id){
        return trackRepository.findById(id);
    }

    @Override
    public Iterable<Track> findAllTracks() {
        return trackRepository.findAll();
    }

    public Page<Track> findAllTracksPage(int page, int size) {
        return trackRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public void deleteTrackById(String id) {
        trackRepository.deleteById(id);
    }

    @Override
    public void deleteAllTracks() {trackRepository.deleteAll();}

    public List<Track> searchTracksByTempoAndDuration(double minTempo, int maxDuration, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return trackRepository.findTracksByTempoAndDuration(minTempo, maxDuration, pageable);
    }

    public List<Track> findTracksByArtistIdAndEnergyRangeWithAvgTempoAggregation(String artistId, double minEnergy, double maxEnergy) {
        // Building the query with aggregation
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> b
                                .must(m -> m
                                        .term(t -> t.field("artistId").value(artistId))
                                )
                                .must(m -> m
                                        .range(r -> r.field("energy").gte(JsonData.of(minEnergy)).lte(JsonData.of(maxEnergy)))
                                )
                        )
                )
                .withAggregation("avg_tempo", Aggregation.of(a -> a
                        .avg(avg -> avg.field("tempo"))
                ))
                .withSort(s -> s.field(f -> f
                        .field("danceability")
                        .order(SortOrder.Desc)
                ))
                .build();

        // Execute the query
        SearchHits<Track> searchHits = elasticsearchOperations.search(query, Track.class);

//        Double avgTempoValue = null;
//        if (searchHits.hasAggregations()) {
//            ElasticsearchAggregations aggregations = (ElasticsearchAggregations) searchHits.getAggregations();;
//
//            Aggregate avgTempoAggregate = aggregations.get("avg_tempo");
//            if (avgTempoAggregate != null && avgTempoAggregate.isAvg()) {
//                AvgAggregate avgAgg = avgTempoAggregate.avg();
//                if (avgAgg != null) {
//                    avgTempoValue = avgAgg.value();
//                }
//            }
//        }

        //System.out.println(avgTempoValue);
        // Construct and return the custom response
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public List<Track> findTracksInPlaylistWithAvgPopularityAggregation(String playlistId, Pageable pageable) {
        // Building the query
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> b
                                .must(m -> m.term(t -> t.field("playlistId").value(playlistId)))
                        )
                )
                .withAggregation("avg_popularity", Aggregation.of(a -> a
                        .avg(avg -> avg.field("popularity")) // Calculate average popularity
                ))
                .withSort(s -> s.field(f -> f
                        .field("popularity")
                        .order(SortOrder.Desc) // Sort by popularity in descending order
                ))
                .withPageable(pageable)
                .build();

        // Execute the query
        SearchHits<Track> searchHits = elasticsearchOperations.search(query, Track.class);

        // Construct and return the custom response
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
