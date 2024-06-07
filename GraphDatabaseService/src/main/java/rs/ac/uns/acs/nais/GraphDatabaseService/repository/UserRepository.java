package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("CREATE (u:CollectionUser {userId: $userId, favoriteSongs: []}) RETURN u")
    User createUser(Long userId);

    @Query("MATCH (u:CollectionUser {userId: $userId}) RETURN u LIMIT 1")
    Optional<User> getUserById(Long userId);

    @Query("MATCH (u:CollectionUser) RETURN u")
    List<User> getAllUsers();

    @Query("MATCH (u:CollectionUser {userId: $userId}) SET u.favoriteSongs = $favoriteSongs RETURN u")
    User updateUser(Long userId, List<String> favoriteSongs);

    @Query("MATCH (u:CollectionUser {userId: $userId}) DETACH DELETE u")
    void deleteUser(Long userId);

    @Query("MATCH (u:CollectionUser {userId: $userId})-[:FAVORITES]->(s:CollectionSong) RETURN s")
    List<Song> getAllFavoriteSongs(Long userId);

    @Query("MATCH (u:CollectionUser {userId: $userId}) WHERE ANY(song IN u.favoriteSongs WHERE song = $trackId) RETURN COUNT(u) > 0")
    boolean hasFavoriteSong(Long userId, String trackId);
}
