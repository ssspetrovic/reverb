package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.json.JsonData;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Album;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TrackRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITrackService;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    public long countAllTracks(){return trackRepository.count();}

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

    public Page<Track> findTracksByArtistAndLiveness(String artistId, Double minLiveness, Pageable page) {
        return trackRepository.findTracksByTempoAndArtistId(artistId, minLiveness, page);
    }

    @Override
    public byte[] export(List<Track> tracks) throws IOException, DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        String directoryPath = "pdf";
        String filename = directoryPath + "/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        };

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        Paragraph title = new Paragraph("TRACKS REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable reportTable = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Track Name", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Popularity", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Liveness", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);

        for (Track track : tracks) {
            reportTable.addCell(track.getName() != null ? track.getName() : "N/A");
            reportTable.addCell(track.getPopularity() != null ? String.valueOf(track.getPopularity()) : "N/A");
            reportTable.addCell(track.getLiveness() != null ? String.valueOf(track.getLiveness()) : "N/A");
        }

        document.add(reportTable);
        document.close();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }
}
