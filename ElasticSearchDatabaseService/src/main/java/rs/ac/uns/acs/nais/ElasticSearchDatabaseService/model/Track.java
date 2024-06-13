package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Setter
@Getter
@Data
@Document(indexName = "tracks")
public class Track {
    @Id
    private String id; // Unique identifier for the track

    @Field(type = FieldType.Text)
    private String name; // The name of the track, analyzed for full-text search

    @Field(type = FieldType.Integer)
    private Integer popularity; // Popularity score of the track

    @Field(type = FieldType.Double)
    private Double danceability; // Danceability score of the track

    @Field(type = FieldType.Double)
    private Double energy; // Energy level of the track

    @Field(type = FieldType.Integer)
    private Integer key; // Musical key of the track

    @Field(type = FieldType.Double)
    private Double loudness; // Loudness of the track in decibels

    @Field(type = FieldType.Integer)
    private Integer mode; // Mode of the track (major or minor)

    @Field(type = FieldType.Double)
    private Double speechiness; // Speechiness score of the track

    @Field(type = FieldType.Double)
    private Double acousticness; // Acousticness score of the track

    @Field(type = FieldType.Double)
    private Double instrumentalness; // Instrumentalness score of the track

    @Field(type = FieldType.Double)
    private Double liveness; // Liveness score of the track

    @Field(type = FieldType.Double)
    private Double valence; // Valence score (musical positiveness) of the track

    @Field(type = FieldType.Double)
    private Double tempo; // Tempo of the track in BPM (Beats Per Minute)

    @Field(type = FieldType.Integer)
    private Integer duration_ms; // Duration of the track in milliseconds

    @Field(type = FieldType.Keyword)
    private String albumId; // ID of the album the track belongs to

    @Field(type = FieldType.Keyword)
    private String artistId; // ID of the artist who performed the track

    @Field(type = FieldType.Keyword)
    private String playlistId; // ID of the playlist the track is part of

    public Track() {
        // Default constructor
    }
}
