package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaylistGenreCountDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PlaylistCountAveragePopularityDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.Top3SubgenresPerGenreDTO;

import java.util.List;

@Repository
public interface PlaylistRepository extends Neo4jRepository<Playlist, Long> {

    @Query("CREATE (p:CollectionPlaylist {playlist_id: $playlist_id, name: $name, genre: $genre, subgenre: $subgenre}) RETURN p")
    Playlist createPlaylist(String playlist_id, String name, String genre, String subgenre);

    @Query("MATCH (p:CollectionPlaylist {playlist_id: $playlist_id}) "
            + "WITH p LIMIT 1 "
            + "SET p.name = $name, p.genre = $genre, p.subgenre = $subgenre "
            + "RETURN p")
    Playlist updatePlaylist(String playlist_id, String name, String genre, String subgenre);

    @Query("MATCH (p:CollectionPlaylist {playlist_id: $playlist_id})-[r]-() DELETE p, r")
    void deletePlaylist(String playlist_id);

    @Query("MATCH (p:CollectionPlaylist {playlist_id: $playlist_id}) RETURN p LIMIT 1")
    Playlist getPlaylistById(String playlist_id);

    @Query("MATCH (p:CollectionPlaylist) RETURN p")
    List<Playlist> getAllPlaylists();

    @Query("MATCH (p:CollectionPlaylist) WHERE p.genre = $genre RETURN p")
    List<Playlist> getPlaylistsByGenre(String genre);

    @Query("MATCH (p:CollectionPlaylist) WHERE p.subgenre = $subgenre RETURN p")
    List<Playlist> getPlaylistsBySubgenre(String subgenre);

    @Query("MATCH (p:CollectionPlaylist {name: $playlist_name})<-[:INCLUDED_IN_PLAYLIST]-(s:CollectionSong {track_id: $track_id}) "
            + "SET p.playlist_genre = $new_genre "
            + "RETURN p")
    Playlist updatePlaylistGenreBySong(String playlist_name, String track_id, String new_genre);

    @Query("MATCH (s:CollectionSong)-[:INCLUDED_IN_PLAYLIST]->(p:CollectionPlaylist) "
            + "WITH p, COUNT(s) AS songCount "
            + "RETURN p AS playlist, songCount "
            + "ORDER BY songCount DESC")
    List<PlaylistGenreCountDTO> getPlaylistGenreWithSongCount();

    @Query("MATCH (p:CollectionPlaylist)<-[:INCLUDED_IN_PLAYLIST]-(s:CollectionSong) "
            + "WITH p, COUNT(s) AS numSongs, AVG(s.track_popularity) AS avgPopularity "
            + "RETURN p AS playlist, numSongs, avgPopularity "
            + "ORDER BY numSongs DESC")
    List<PlaylistCountAveragePopularityDTO> getPlaylistCountAndAveragePopularity();

    @Query("MATCH (p:CollectionPlaylist) "
                + "WHERE p.genre IS NOT NULL "
                + "RETURN p, COUNT(*) AS songCount "
                + "ORDER BY songCount DESC "
                + "LIMIT 10;")
    List<PlaylistGenreCountDTO> getTop10Genres();

    @Query("MATCH (p:CollectionPlaylist) "
                + "WHERE p.genre IS NOT NULL AND p.subgenre IS NOT NULL "
                + "WITH p.genre AS genre, p.subgenre AS subgenre, COUNT(*) AS songCount "
                + "ORDER BY genre, songCount DESC "
                + "WITH genre, COLLECT({subgenre: subgenre}) AS subgenres "
                + "RETURN p, subgenres[..3] AS topSubgenres;")
    List<Top3SubgenresPerGenreDTO> getTop3SubgenresPerGenre();
}
