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
    private String id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Keyword, name = "genre")
    private String genre;
    @Field(type = FieldType.Keyword, name = "subgenre")
    private String subgenre;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Track> tracks;
}
