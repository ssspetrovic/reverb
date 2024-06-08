package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Setter
@Getter
@Data
@Document(indexName = "albums")
public class Album {
    @Id
    private String id;

    private String name;
    private String releaseDate;

    @Field(type = FieldType.Keyword)
    private String artistId;

    @Field(type = FieldType.Keyword)
    private List<String> trackIds;

    public Album() {
    }

}