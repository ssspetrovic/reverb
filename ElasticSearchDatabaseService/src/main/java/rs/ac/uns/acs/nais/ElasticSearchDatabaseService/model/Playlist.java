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
@Document(indexName = "playlists")
public class Playlist {
    @Id
    private String Id;

    private String name;
    private String genre;
    private String subgenre;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Track> tracks;

    public Playlist(){

    }



}
