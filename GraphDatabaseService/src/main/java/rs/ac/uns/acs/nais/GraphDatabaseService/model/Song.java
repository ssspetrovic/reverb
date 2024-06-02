package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import java.util.HashMap;
import java.util.Map;

@Node("CollectionSong")
public class Song {

    @RelationshipId @GeneratedValue
    private Long id;
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

    public Song() {
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.id);
        map.put("track_id", this.track_id);
        map.put("track_name", this.track_name);
        map.put("track_artist", this.track_artist);
        map.put("track_popularity", this.track_popularity);
        map.put("track_album_id", this.track_album_id);
        map.put("track_album_name", this.track_album_name);
        map.put("track_album_release_date", this.track_album_release_date);
        map.put("playlist_name", this.playlist_name);
        map.put("playlist_id", this.playlist_id);
        map.put("playlist_genre", this.playlist_genre);
        map.put("playlist_subgenre", this.playlist_subgenre);
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
        map.put("duration_ms", this.duration_ms);
        return map;
    }
 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackId() {
        return track_id;
    }

    public void setTrackId(String track_id) {
        this.track_id = track_id;
    }

    public String getTrackName() {
        return track_name;
    }

    public void setTrackName(String track_name) {
        this.track_name = track_name;
    }

    public String getTrackArtist() {
        return track_artist;
    }

    public void setTrackArtist(String track_artist) {
        this.track_artist = track_artist;
    }

    public Integer getTrackPopularity() {
        return track_popularity;
    }

    public void setTrackPopularity(Integer track_popularity) {
        this.track_popularity = track_popularity;
    }

    public String getTrackAlbum_id() {
        return track_album_id;
    }

    public void setTrackAlbum_id(String track_album_id) {
        this.track_album_id = track_album_id;
    }

    public String getTrackAlbum_name() {
        return track_album_name;
    }

    public void setTrackAlbumName(String track_album_name) {
        this.track_album_name = track_album_name;
    }

    public String getTrackAlbumReleaseDate() {
        return track_album_release_date;
    }

    public void setTrackAlbumReleaseDate(String track_album_release_date) {
        this.track_album_release_date = track_album_release_date;
    }

    public String getPlaylistName() {
        return playlist_name;
    }

    public void setPlaylistName(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public String getPlaylistId() {
        return playlist_id;
    }

    public void setPlaylistId(String playlist_id) {
        this.playlist_id = playlist_id;
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

    public String getDanceability() {
        return danceability;
    }

    public void setDanceability(String danceability) {
        this.danceability = (danceability != null) ? danceability : "0.0";
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = (energy != null) ? energy : "0.0";
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getLoudness() {
        return loudness;
    }

    public void setLoudness(String loudness) {
        this.loudness = (loudness != null) ? loudness : "0.0";
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(String speechiness) {
        this.speechiness = (speechiness != null) ? speechiness : "0.0";
    }

    public String getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(String acousticness) {
        this.acousticness = (acousticness != null) ? acousticness : "0.0";
    }

    public String getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(String instrumentalness) {
        this.instrumentalness = (instrumentalness != null) ? instrumentalness : "0.0";
    }

    public String getLiveness() {
        return liveness;
    }

    public void setLiveness(String liveness) {
        this.liveness = (liveness != null) ? liveness : "0.0";
    }

    public String getValence() {
        return valence;
    }

    public void setValence(String valence) {
        this.valence = (valence != null) ? valence : "0.0";
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = (tempo != null) ? tempo : "0.0";
    }

    public Integer getDurationMs() {
        return duration_ms;
    }

    public void setDurationMs(Integer duration_ms) {
        this.duration_ms = duration_ms;
    }
}
