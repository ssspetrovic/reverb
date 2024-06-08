package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Track;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITrackService;

import java.util.Optional;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private ITrackService trackService;

    @PostMapping
    public Track saveTrack(@RequestBody Track track){
        return trackService.saveTrack(track);
    }

    @GetMapping("/{id}")
    public Optional<Track> findTrackById(@PathVariable String id){
        return trackService.findTrackById(id);
    }

    @GetMapping
    public Iterable<Track> findAllTracks(){
        return trackService.findAllTracks();
    }

    @DeleteMapping("/{id}")
    public void deleteTrackById(@PathVariable String id){
        trackService.deleteTrackById(id);
    }

}
