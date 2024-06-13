package rs.ac.uns.acs.nais.RelationalDatabaseService.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "popularity")
    private Integer popularity;

    @Column(name = "danceability")
    private Double danceability;

    @Column(name = "energy")
    private Double energy;

    @Column(name = "key_value")
    private Integer key; // "key" is a reserved keyword in some databases, use "key_value" instead

    @Column(name = "loudness")
    private Double loudness;

    @Column(name = "mode")
    private Integer mode;

    @Column(name = "speechiness")
    private Double speechiness;

    @Column(name = "acousticness")
    private Double acousticness;

    @Column(name = "instrumentalness")
    private Double instrumentalness;

    @Column(name = "liveness")
    private Double liveness;

    @Column(name = "valence")
    private Double valence;

    @Column(name = "tempo")
    private Double tempo;

    @Column(name = "duration_ms")
    private Integer duration_ms;

    @Column(name = "album_id", nullable = false)
    private String albumId;

    @Column(name = "artist_id", nullable = false)
    private String artistId;

    @Column(name = "playlist_id")
    private String playlistId;

    public Track(String id, String name, Integer popularity, Double danceability, Double energy, Integer key, Double loudness, Integer mode, Double speechiness, Double acousticness, Double instrumentalness, Double liveness, Double valence, Double tempo, Integer duration_ms, String albumId, String artistId, String playlistId) {
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
        this.duration_ms = duration_ms;
        this.albumId = albumId;
        this.artistId = artistId;
        this.playlistId = playlistId;
    }

    public Track() {
    }
}
