package rs.ac.uns.acs.nais.OrchestratorService.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDTO {
    private String id;
    private String name;
    private Integer popularity;
    private Double danceability;
    private Double energy;
    private Integer key;
    private Double loudness;
    private Integer mode;
    private Double speechiness;
    private Double acousticness;
    private Double instrumentalness;
    private Double liveness;
    private Double valence;
    private Double tempo;
    private Integer durationMs;
    private String albumId;
    private String artistId;
    private String playlistId;

    public TrackDTO(String id, String name, Integer popularity, Double danceability, Double energy, Integer key, Double loudness, Integer mode, Double speechiness, Double acousticness, Double instrumentalness, Double liveness, Double valence, Double tempo, Integer durationMs, String albumId, String artistId, String playlistId) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.danceability = danceability;
        this.energy = energy;
        this.key = key;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valence = valence;
        this.tempo = tempo;
        this.durationMs = durationMs;
        this.albumId = albumId;
        this.artistId = artistId;
        this.playlistId = playlistId;
    }

    public TrackDTO(){

    }
}
