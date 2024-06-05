package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Setter
@Getter
@Document(indexName = "tracks")
public class Track {
    @Id
    private String track_id;
    private String track_name;
    private String track_artist;
    private Integer track_popularity;
    private String track_album_id;
    private String track_album_name;
    private String track_album_release_date;
    private String playlist_name;
    private String playlist_id;
    private String playlist_genre;
    private String playlist_subgenre;
    private String danceability;
    private String energy;
    private Integer key;
    private String loudness;
    private Integer mode;
    private String speechiness;
    private String acousticness;
    private String instrumentalness;
    private String liveness;
    private String valence;
    private String tempo;
    private Integer duration_ms;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Album album;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Artist artist;

    public Track(){

    }

}
