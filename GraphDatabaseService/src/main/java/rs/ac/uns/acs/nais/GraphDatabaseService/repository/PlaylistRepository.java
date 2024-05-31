package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;

import java.util.List;

@Repository
public interface PlaylistRepository extends Neo4jRepository<Playlist, Long> {

    @Query("CREATE (p:Playlist $props) RETURN p")
    Playlist createPlaylist(Playlist playlist);

    @Query("MATCH (p:Playlist {id: $playlistId}) SET p = $props RETURN p")
    Playlist updatePlaylist(String playlistId, Playlist playlist);

    @Query("MATCH (p:Playlist {id: $playlistId}) DELETE p")
    void deletePlaylist(String playlistId);

    @Query("MATCH (p:Playlist {id: $playlistId}) RETURN p")
    Playlist getPlaylistById(String playlistId);

    @Query("MATCH (p:Playlist) RETURN p")
    List<Playlist> getAllPlaylists();

    @Query("MATCH (p:Playlist) WHERE p.genre = $genre RETURN p")
    List<Playlist> getPlaylistsByGenre(String genre);

    @Query("MATCH (p:Playlist) WHERE p.subgenre = $subgenre RETURN p")
    List<Playlist> getPlaylistsBySubgenre(String subgenre);
}
