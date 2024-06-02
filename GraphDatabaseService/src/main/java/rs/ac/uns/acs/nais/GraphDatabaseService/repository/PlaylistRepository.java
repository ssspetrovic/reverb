package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Playlist;

import java.util.List;

@Repository
public interface PlaylistRepository extends Neo4jRepository<Playlist, Long> {

    @Query("CREATE (p:CollectionPlaylist {playlist_id: $playlist.playlist_id, name: $playlist.name, genre: $playlist.genre, subgenre: $playlist.subgenre}) RETURN p")
    Playlist createPlaylist(Playlist playlist);

    @Query("MATCH (p:CollectionPlaylist {playlist_id: $playlist.playlist_id}) SET p.name = $playlist.name, p.genre = $playlist.genre, p.subgenre = $playlist.subgenre RETURN p")
    Playlist updatePlaylist(Playlist playlist);

    @Query("MATCH (p:CollectionPlaylist {playlist_id: $playlist_id}) DELETE p")
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
