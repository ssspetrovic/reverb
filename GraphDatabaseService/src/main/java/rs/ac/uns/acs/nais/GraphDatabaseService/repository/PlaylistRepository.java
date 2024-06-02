package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;

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
}
