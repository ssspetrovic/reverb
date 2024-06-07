package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.DataLoadingService;

import java.io.IOException;


@RestController
@RequestMapping("/data")
public class DataLoadingController {

    private final DataLoadingService dataLoadingService;

    @Autowired
    public DataLoadingController(DataLoadingService dataLoadingService) {
        this.dataLoadingService = dataLoadingService;
    }

    @PostMapping("/loadArtists")
    public String loadArtists() {
        try {
            dataLoadingService.loadArtists();
            return "Artists loaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to load artists: " + e.getMessage();
        }
    }

    @PostMapping("/loadAlbums")
    public String loadAlbums() {
        try {
            dataLoadingService.loadAlbums();
            return "Albums loaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to load albums: " + e.getMessage();
        }
    }
}
