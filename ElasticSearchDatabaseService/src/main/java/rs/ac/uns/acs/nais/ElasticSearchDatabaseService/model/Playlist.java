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

    private String playlist_name;
    private String playlist_genre;
    private String playlist_subgenre;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Track> tracks;

    public Playlist(){

    }

    public String getPlaylist_subgenre() {
        return playlist_subgenre;
    }

    public void setPlaylist_subgenre(String playlist_subgenre) {
        this.playlist_subgenre = playlist_subgenre;
    }

    public String getPlaylist_genre() {
        return playlist_genre;
    }

    public void setPlaylist_genre(String playlist_genre) {
        this.playlist_genre = playlist_genre;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
