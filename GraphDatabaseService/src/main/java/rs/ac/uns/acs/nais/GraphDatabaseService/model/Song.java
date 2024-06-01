package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import java.util.HashMap;
import java.util.Map;

@Node("CollectionSong")
public class Song {

    @Id @GeneratedValue
    private Long id;
    private String trackId;
    private String trackName;
    private String trackArtist;
    private Integer trackPopularity;
    private String trackAlbumId;
    private String trackAlbumName;
    private String trackAlbumReleaseDate;
    private String playlistName;
    private String playlistId;
    private String playlistGenre;
    private String playlistSubgenre;
    private Float danceability;
    private Float energy;
    private Integer key;
    private Float loudness;
    private Integer mode;
    private Float speechiness;
    private Float acousticness;
    private String instrumentalness;
    private Float liveness;
    private Float valence;
    private Float tempo;
    private Integer durationMs;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTrackArtist() {
        return trackArtist;
    }

    public void setTrackArtist(String trackArtist) {
        this.trackArtist = trackArtist;
    }

    public Integer getTrackPopularity() {
        return trackPopularity;
    }

    public void setTrackPopularity(Integer trackPopularity) {
        this.trackPopularity = trackPopularity;
    }

    public String getTrackAlbumId() {
        return trackAlbumId;
    }

    public void setTrackAlbumId(String trackAlbumId) {
        this.trackAlbumId = trackAlbumId;
    }

    public String getTrackAlbumName() {
        return trackAlbumName;
    }

    public void setTrackAlbumName(String trackAlbumName) {
        this.trackAlbumName = trackAlbumName;
    }

    public String getTrackAlbumReleaseDate() {
        return trackAlbumReleaseDate;
    }

    public void setTrackAlbumReleaseDate(String trackAlbumReleaseDate) {
        this.trackAlbumReleaseDate = trackAlbumReleaseDate;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistGenre() {
        return playlistGenre;
    }

    public void setPlaylistGenre(String playlistGenre) {
        this.playlistGenre = playlistGenre;
    }

    public String getPlaylistSubgenre() {
        return playlistSubgenre;
    }

    public void setPlaylistSubgenre(String playlistSubgenre) {
        this.playlistSubgenre = playlistSubgenre;
    }

    public Float getDanceability() {
        return danceability;
    }

    public void setDanceability(Float danceability) {
        this.danceability = danceability;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Float getLoudness() {
        return loudness;
    }

    public void setLoudness(Float loudness) {
        this.loudness = loudness;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(Float speechiness) {
        this.speechiness = speechiness;
    }

    public Float getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(Float acousticness) {
        this.acousticness = acousticness;
    }

    public String getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(String instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public Float getLiveness() {
        return liveness;
    }

    public void setLiveness(Float liveness) {
        this.liveness = liveness;
    }

    public Float getValence() {
        return valence;
    }

    public void setValence(Float valence) {
        this.valence = valence;
    }

    public Float getTempo() {
        return tempo;
    }

    public void setTempo(Float tempo) {
        this.tempo = tempo;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }
}
