package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongPopularityProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MostPopularSongInPlaylistDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongTempoProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.HighEnergyMusicProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.LongestSongInEveryAlbumProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PerformedByProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.IncludedInProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.IncludedInPlaylistProjection;

import java.util.List;
import java.util.Map;

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

    @Query("MATCH (s:CollectionSong) "
            + "WHERE toFloat(s.energy) > 0.8 "
            + "WITH s, s.track_popularity + 10 AS newPopularity "
            + "SET s.track_popularity = newPopularity "
            + "RETURN s.track_name, s.track_popularity")
    List<SongPopularityProjection> updatePopularityBasedOnEnergy();

    @Query("MATCH (s:CollectionSong) "
            + "WHERE s.track_popularity > 80 "
            + "SET s.tempo = toString(ROUND(toFloat(s.tempo) + 3, 2)) "
            + "WITH s "
            + "RETURN s")
    List<SongTempoProjection> updateTempoBasedOnPopularity();

    @Query("MATCH (p:CollectionPlaylist)<-[:INCLUDED_IN_PLAYLIST]-(s:CollectionSong) "
            + "WITH p, s "
            + "ORDER BY s.track_popularity DESC "
            + "WITH p, COLLECT(s)[0] AS popularSong, s "
            + "RETURN s")
    List<MostPopularSongInPlaylistDTO> getMostPopularSongsFromEachPlaylist();

    @Query("MATCH (s:CollectionSong)-[:INCLUDED_IN_PLAYLIST]->(p:CollectionPlaylist) "
            + "WHERE p.genre = $playlist_genre AND toFloat(s.energy) > 0.75 "
            + "RETURN s " 
            + "ORDER BY toFloat(s.energy) DESC")
    List<HighEnergyMusicProjection> getHighEnergyMusicBasedOnGenre(String playlist_genre);

    @Query("MATCH (al:CollectionAlbum)<-[:INCLUDED_IN]-(s:CollectionSong) "
            + "WITH al, s "
            + "ORDER BY s.duration_ms DESC "
            + "WITH al, COLLECT(s)[0] AS longestSong, s "
            + "RETURN s")
    List<LongestSongInEveryAlbumProjection> getLongestSongInEveryAlbum();

    @Query("MATCH (s:CollectionSong {track_id: $trackId}), (a:CollectionArtist {name: $name}) "
                + "CREATE (s)-[:PERFORMED_BY]->(a)")
    void createPerformedByRelationship(@Param("trackId") String trackId, @Param("name") String name);

    @Query("MATCH (s:CollectionSong {track_id: $trackId}), (al:CollectionAlbum {name: $albumName}) "
                + "CREATE (s)-[:INCLUDED_IN]->(al)")
    void createIncludedInRelationship(@Param("trackId") String trackId, @Param("albumName") String albumName);

    @Query("MATCH (s:CollectionSong {track_id: $trackId}), (p:CollectionPlaylist {playlist_id: $playlistId}) "
                + "CREATE (s)-[:INCLUDED_IN_PLAYLIST]->(p)")
    void createIncludedInPlaylistRelationship(@Param("trackId") String trackId, @Param("playlistId") String playlistId);

    @Query("MATCH (s:CollectionSong)-[:PERFORMED_BY]->(a:CollectionArtist) "
                + "RETURN s")
    List<PerformedByProjection> getAllPerformedByRelationships();

    @Query("MATCH (s:CollectionSong)-[:INCLUDED_IN]->(al:CollectionAlbum) "
                + "RETURN s")
    List<IncludedInProjection> getAllIncludedInRelationships();

    @Query("MATCH (s:CollectionSong)-[:INCLUDED_IN_PLAYLIST]->(p:CollectionPlaylist) "
                + "RETURN s")
    List<IncludedInPlaylistProjection> getAllIncludedInPlaylistRelationships();

    @Query("MATCH (s:CollectionSong {track_id: $trackId})-[r:PERFORMED_BY]->(a:CollectionArtist) "
                + "DELETE r "
                + "WITH s, a "
                + "MATCH (newArtist:CollectionArtist {name: $name}) "
                + "CREATE (s)-[:PERFORMED_BY]->(newArtist)")
    void updatePerformedByRelationship(@Param("trackId") String trackId, @Param("name") String name);

    @Query("MATCH (s:CollectionSong {track_id: $trackId})-[r:INCLUDED_IN]->(al:CollectionAlbum) "
                + "DELETE r "
                + "WITH s, al "
                + "MATCH (newAlbum:CollectionAlbum {name: $albumName}) "
                + "CREATE (s)-[:INCLUDED_IN]->(newAlbum)")
    void updateIncludedInRelationship(@Param("trackId") String trackId, @Param("albumName") String albumName);

    @Query("MATCH (s:CollectionSong {track_id: $trackId})-[r:INCLUDED_IN_PLAYLIST]->(p:CollectionPlaylist) "
                + "DELETE r " 
                + "WITH s, p " 
                + "MATCH (newPlaylist:CollectionPlaylist {playlist_id: $playlistId}) " 
                + "CREATE (s)-[:INCLUDED_IN_PLAYLIST]->(newPlaylist)")
    void updateIncludedInPlaylistRelationship(@Param("trackId") String trackId, @Param("playlistId") String playlistId);

    @Query("MATCH (s:CollectionSong {track_id: $trackId})-[r:PERFORMED_BY]->(a:CollectionArtist) " 
                + "DELETE r")
    void deletePerformedByRelationship(@Param("trackId") String trackId);

    @Query("MATCH (s:CollectionSong {track_id: $trackId})-[r:INCLUDED_IN]->(al:CollectionAlbum) "
                + "DELETE r")
    void deleteIncludedInRelationship(@Param("trackId") String trackId);

    @Query("MATCH (s:CollectionSong {track_id: $trackId})-[r:INCLUDED_IN_PLAYLIST]->(p:CollectionPlaylist) "
                + "DELETE r")
    void deleteIncludedInPlaylistRelationship(@Param("trackId") String trackId);

    @Query("WITH {genre: $genre, subgenre: $subgenre, artist: $artist} AS params " +
           "MATCH (song:CollectionSong)-[:INCLUDED_IN_PLAYLIST]->(playlist:CollectionPlaylist) " +
           "WHERE " +
           "(CASE WHEN params.genre IS NULL OR params.genre = '' THEN TRUE ELSE playlist.genre = params.genre END) AND " +
           "(CASE WHEN params.subgenre IS NULL OR params.subgenre = '' THEN TRUE ELSE playlist.subgenre = params.subgenre END) AND " +
           "(CASE WHEN params.artist IS NULL OR params.artist = '' THEN TRUE ELSE song.track_artist = params.artist END) " +
           "WITH COLLECT(song) AS exactMatches, params " +
           "WITH exactMatches, params " +
           "MATCH (song:CollectionSong)-[:INCLUDED_IN_PLAYLIST]->(playlist:CollectionPlaylist) " +
           "WHERE " +
           "(CASE WHEN params.genre IS NULL OR params.genre = '' THEN TRUE ELSE playlist.genre = params.genre END) AND " +
           "(CASE WHEN params.subgenre IS NULL OR params.subgenre = '' THEN TRUE ELSE playlist.subgenre = params.subgenre END) " +
           "WITH exactMatches, COLLECT(song) AS genreAndSubgenreMatches, params " +
           "WITH exactMatches + genreAndSubgenreMatches AS combinedMatches, params " +
           "MATCH (song:CollectionSong)-[:INCLUDED_IN_PLAYLIST]->(playlist:CollectionPlaylist) " +
           "WHERE " +
           "(CASE WHEN params.genre IS NULL OR params.genre = '' THEN TRUE ELSE playlist.genre = params.genre END) " +
           "WITH combinedMatches, COLLECT(song) AS allGenreMatches " +
           "UNWIND combinedMatches + allGenreMatches AS song " +
           "RETURN COLLECT(song)[..20] AS finalSongs;")
    List<Song> recommendSongs(@Param("genre") String genre, @Param("subgenre") String subgenre, @Param("artist") String artist);

    @Query("MATCH (s:CollectionSong) "
                + "WHERE s.track_popularity IS NOT NULL "
                + "WITH s.track_name AS trackName, s "
                + "ORDER BY s.track_popularity DESC "
                + "WITH DISTINCT trackName, s "
                + "RETURN DISTINCT trackName "
                + "LIMIT 50;")
    List<String> getTop50Songs();

}
