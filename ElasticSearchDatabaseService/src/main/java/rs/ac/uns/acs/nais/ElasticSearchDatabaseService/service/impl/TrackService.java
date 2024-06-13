package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.json.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dtos.TrackDTO;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TrackRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITrackService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrackService implements ITrackService {

    private final TrackRepository trackRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public TrackService(ElasticsearchOperations elasticsearchOperations, TrackRepository trackRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.trackRepository = trackRepository;
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

    @KafkaListener(topics = "elastic-search-service-topic", groupId = "elastic-group")
    public void handleTrackMessage(TrackDTO trackDTO) {
        // Convert DTO to Elasticsearch document and save
        try {
            Track track = new Track();
            // Map fields from trackDTO to track entity
            track.setId(trackDTO.getId());
            track.setName(trackDTO.getName());
            track.setPopularity(trackDTO.getPopularity());
            track.setDanceability(trackDTO.getDanceability());
            track.setEnergy(trackDTO.getEnergy());
            track.setKey(trackDTO.getKey());
            track.setLoudness(trackDTO.getLoudness());
            track.setMode(trackDTO.getMode());
            track.setSpeechiness(trackDTO.getSpeechiness());
            track.setAcousticness(trackDTO.getAcousticness());
            track.setInstrumentalness(trackDTO.getInstrumentalness());
            track.setLiveness(trackDTO.getLiveness());
            track.setValence(trackDTO.getValence());
            track.setTempo(trackDTO.getTempo());
            track.setDuration_ms(trackDTO.getDuration_ms());
            track.setAlbumId(trackDTO.getAlbumId());
            track.setArtistId(trackDTO.getArtistId());
            track.setPlaylistId(trackDTO.getPlaylistId());

            trackRepository.save(track);
            kafkaTemplate.send("track-saga-success", "Track ID: " + trackDTO.getId() + " processed successfully.");
        } catch (Exception e) {
            kafkaTemplate.send("track-saga-fail", "Track ID: " + trackDTO.getId() + " failed to process.");
        }
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
