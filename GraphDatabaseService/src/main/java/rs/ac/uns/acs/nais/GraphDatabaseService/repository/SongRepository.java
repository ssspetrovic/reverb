package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends Neo4jRepository<Song, Long> {

    @Query("CREATE (s:CollectionSong {"
            + "track_id: $track_id, "
            + "track_name: $track_name, "
            + "track_artist: $track_artist, "
            + "track_popularity: $track_popularity, "
            + "track_album_id: $track_album_id, "
            + "track_album_name: $track_album_name, "
            + "track_album_release_date: $track_album_release_date, "
            + "playlist_name: $playlist_name, "
            + "playlist_id: $playlist_id, "
            + "playlist_genre: $playlist_genre, "
            + "playlist_subgenre: $playlist_subgenre, "
            + "danceability: $danceability, "
            + "energy: $energy, "
            + "key: $key, "
            + "loudness: $loudness, "
            + "mode: $mode, "
            + "speechiness: $speechiness, "
            + "acousticness: $acousticness, "
            + "instrumentalness: $instrumentalness, "
            + "liveness: $liveness, "
            + "valence: $valence, "
            + "tempo: $tempo, "
            + "duration_ms: $duration_ms "
            + "}) RETURN s")
    Song createSong(String track_id,
                    String track_name,
                    String track_artist,
                    Integer track_popularity,
                    String track_album_id,
                    String track_album_name,
                    String track_album_release_date,
                    String playlist_name,
                    String playlist_id,
                    String playlist_genre,
                    String playlist_subgenre,
                    String danceability,
                    String energy,
                    Integer key,
                    String loudness,
                    Integer mode,
                    String speechiness,
                    String acousticness,
                    String instrumentalness,
                    String liveness,
                    String valence,
                    String tempo,
                    Integer duration_ms);

    @Query("MATCH (s:CollectionSong {track_id: $track_id}) SET "
            + "s.track_name = $track_name, "
            + "s.track_artist = $track_artist, "
            + "s.track_popularity = $track_popularity, "
            + "s.track_album_id = $track_album_id, "
            + "s.track_album_name = $track_album_name, "
            + "s.track_album_release_date = $track_album_release_date, "
            + "s.playlist_name = $playlist_name, "
            + "s.playlist_id = $playlist_id, "
            + "s.playlist_genre = $playlist_genre, "
            + "s.playlist_subgenre = $playlist_subgenre, "
            + "s.danceability = $danceability, "
            + "s.energy = $energy, "
            + "s.key = $key, "
            + "s.loudness = $loudness, "
            + "s.mode = $mode, "
            + "s.speechiness = $speechiness, "
            + "s.acousticness = $acousticness, "
            + "s.instrumentalness = $instrumentalness, "
            + "s.liveness = $liveness, "
            + "s.valence = $valence, "
            + "s.tempo = $tempo, "
            + "s.duration_ms = $duration_ms "
            + "RETURN s")
    Song updateSong(String track_id,
                    String track_name,
                    String track_artist,
                    Integer track_popularity,
                    String track_album_id,
                    String track_album_name,
                    String track_album_release_date,
                    String playlist_name,
                    String playlist_id,
                    String playlist_genre,
                    String playlist_subgenre,
                    String danceability,
                    String energy,
                    Integer key,
                    String loudness,
                    Integer mode,
                    String speechiness,
                    String acousticness,
                    String instrumentalness,
                    String liveness,
                    String valence,
                    String tempo,
                    Integer duration_ms);

    @Query("MATCH (s:CollectionSong {track_id: $trackId})-[r]-() DELETE s, r")
    void deleteSong(String trackId);

    @Query("MATCH (s:CollectionSong {track_id: $trackId}) RETURN s LIMIT 1")
    Song getSongById(String trackId);

    @Query("MATCH (s:CollectionSong) RETURN s")
    List<Song> getAllSongs();

    @Query("MATCH (s:CollectionSong)-[:PERFORMED_BY]->(a:CollectionArtist {name: $artistName}) RETURN s")
    List<Song> getSongsByArtist(String artistName);

    @Query("MATCH (s:CollectionSong)-[:INCLUDED_IN]->(al:CollectionAlbum {album_name: $albumName}) RETURN s")
    List<Song> getSongsByAlbum(String albumName);

    @Query("MATCH (s:CollectionSong) WHERE $criteria RETURN s")
    List<Song> searchSongsByCriteria(SongSearchCriteriaDTO criteria);

    @Query("MATCH (s:CollectionSong) WHERE s.track_popularity >= $popularity RETURN s")
    List<Song> searchSongsByPopularity(Integer popularity);

    @Query("MATCH (s:CollectionSong) WHERE s.playlist_genre = $playlistGenre RETURN s")
    List<Song> searchSongsByPlaylistGenre(String playlistGenre);

    @Query("MATCH (s:CollectionSong) WHERE s.energy = $energy RETURN s")
    List<Song> searchSongsByEnergy(String energy);

    @Query("MATCH (s:CollectionSong) WHERE s.playlist_subgenre = $playlistSubgenre RETURN s")
    List<Song> searchSongsByPlaylistSubgenre(String playlistSubgenre);
}
