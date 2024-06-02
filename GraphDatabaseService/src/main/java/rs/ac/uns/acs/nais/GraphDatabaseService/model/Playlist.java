package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.List;

@Node("CollectionPlaylist")
public class Playlist {

    @RelationshipId @GeneratedValue
    private Long id;
    private String playlist_id;
    private String playlist_name;
    private String playlist_genre;
    private String playlist_subgenre;

    @Relationship(type = "INCLUDED_IN_PLAYLIST", direction = Direction.INCOMING) // Definišemo relaciju "INCLUDED_IN_PLAYLIST"
    private List<Song> songs;

    public Playlist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters i setters
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getPlaylistId() {
        return playlist_id;
    }

    public void setPlaylistId(String playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylistName() {
        return playlist_name;
    }

    public void setPlaylistName(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public String getPlaylistGenre() {
        return playlist_genre;
    }

    public void setPlaylistGenre(String playlist_genre) {
        this.playlist_genre = playlist_genre;
    }

    public String getPlaylistSubgenre() {
        return playlist_subgenre;
    }

    public void setPlaylistSubgenre(String playlist_subgenre) {
        this.playlist_subgenre = playlist_subgenre;
    }
}
