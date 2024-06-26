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
@Document(indexName = "artists")
public class Artist {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;


    public Artist() {

    }
}
