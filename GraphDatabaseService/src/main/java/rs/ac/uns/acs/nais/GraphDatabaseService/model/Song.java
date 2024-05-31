package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

@Node
public class Song {

    @Id @GeneratedValue
    private Long id;
    private String trackId;
    private String trackName;
    private String trackArtist;
    private int trackPopularity;
    private String trackAlbumId;
    private String trackAlbumName;
    private String trackAlbumReleaseDate;
    private String playlistName;
    private String playlistId;
    private String playlistGenre;
    private String playlistSubgenre;
    private float danceability;
    private float energy;
    private int key;
    private float loudness;
    private int mode;
    private float speechiness;
    private float acousticness;
    private float instrumentalness;
    private float liveness;
    private float valence;
    private float tempo;
    private int durationMs;

    public Song() {
    }

        public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("trackId", this.trackId);
        map.put("trackName", this.trackName);
        map.put("trackArtist", this.trackArtist);
        map.put("trackPopularity", this.trackPopularity);
        map.put("trackAlbumId", this.trackAlbumId);
        map.put("trackAlbumName", this.trackAlbumName);
        map.put("trackAlbumReleaseDate", this.trackAlbumReleaseDate);
        map.put("playlistName", this.playlistName);
        map.put("playlistId", this.playlistId);
        map.put("playlistGenre", this.playlistGenre);
        map.put("playlistSubgenre", this.playlistSubgenre);
        map.put("danceability", this.danceability);
        map.put("energy", this.energy);
        map.put("key", this.key);
        map.put("loudness", this.loudness);
        map.put("mode", this.mode);
        map.put("speechiness", this.speechiness);
        map.put("acousticness", this.acousticness);
        map.put("instrumentalness", this.instrumentalness);
        map.put("liveness", this.liveness);
        map.put("valence", this.valence);
        map.put("tempo", this.tempo);
        map.put("durationMs", this.durationMs);
        return map;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
}