package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TrackRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITrackService;

import java.util.Optional;

@Service
public class TrackService implements ITrackService {

    @Autowired
    private TrackRepository trackRepository;

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

    @Override
    public void deleteTrackById(String id) {
        trackRepository.deleteById(id);
    }

    @Override
    public void deleteAllTracks() {trackRepository.deleteAll();}
}
