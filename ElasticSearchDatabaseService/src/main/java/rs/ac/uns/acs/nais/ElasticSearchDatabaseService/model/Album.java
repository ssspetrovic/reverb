package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Setter
@Getter
@Document(indexName = "albums")
public class Album {
    @Setter
    @Getter
    @Id
    private String albumId;
    @Setter
    @Getter
    private String albumName;
    private String albumReleaseDate;
    private String artistId;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Artist artist;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Track> tracks;

    public Album() {
    }

}
