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
    private Integer duration_ms;

    @Field(type = FieldType.Keyword)
    private String albumId;

    @Field(type = FieldType.Keyword)
    private String artistId;

    public Track(){

    }

}
