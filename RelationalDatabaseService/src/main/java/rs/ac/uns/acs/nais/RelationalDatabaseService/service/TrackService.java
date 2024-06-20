package rs.ac.uns.acs.nais.RelationalDatabaseService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.RelationalDatabaseService.dtos.TrackDTO;
import rs.ac.uns.acs.nais.RelationalDatabaseService.model.Track;
import rs.ac.uns.acs.nais.RelationalDatabaseService.repository.TrackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public Track createTrack(Track track) {
        return trackRepository.save(track);
    }

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    public Optional<Track> getTrackById(String id) {
        return trackRepository.findById(id);
    }

    @KafkaListener(topics = "relational-service-topic", groupId = "relational-group")
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
            track.setKey_value(trackDTO.getKey());
            track.setLoudness(trackDTO.getLoudness());
            track.setMode(trackDTO.getMode());
            track.setSpeechiness(trackDTO.getSpeechiness());
            track.setAcousticness(trackDTO.getAcousticness());
            track.setInstrumentalness(trackDTO.getInstrumentalness());
            track.setLiveness(trackDTO.getLiveness());
            track.setValence(trackDTO.getValence());
            track.setTempo(trackDTO.getTempo());
            track.setDuration_ms(trackDTO.getDurationMs());
            track.setAlbumId(trackDTO.getAlbumId());
            track.setArtistId(trackDTO.getArtistId());
            track.setPlaylistId(trackDTO.getPlaylistId());

            trackRepository.save(track);
            kafkaTemplate.send("track-saga-success", "Track ID: " + trackDTO.getId() + " processed successfully.");
        } catch (Exception e) {
            kafkaTemplate.send("track-saga-fail", "Track ID: " + trackDTO.getId() + " failed to process.");
        }
    }

    public boolean deleteTrack(String trackId) {
        trackRepository.deleteById(trackId);
        return false;
    }
}