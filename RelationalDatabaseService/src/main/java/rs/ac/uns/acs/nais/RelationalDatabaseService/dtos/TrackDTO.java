package rs.ac.uns.acs.nais.RelationalDatabaseService.dtos;

import lombok.Data;

@Data
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
}
