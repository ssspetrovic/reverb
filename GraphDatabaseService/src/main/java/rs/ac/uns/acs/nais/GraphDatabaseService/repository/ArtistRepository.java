package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends Neo4jRepository<Artist, Long> {

    @Query("CREATE (a:CollectionArtist {name: $name}) SET a.artist_id = id(a)")
    Artist createArtist(String name);

    @Query("MATCH (a:CollectionArtist {name: $name}) SET a.name = $name RETURN a")
    Artist updateArtist(String name);

    @Query("MATCH (a:CollectionArtist {name: $name})-[r]-() DELETE a, r")
    void deleteArtist(String name);

    @Query("MATCH (a:CollectionArtist {name: $name}) RETURN a LIMIT 1")
    Artist getArtistByName(String name);

    @Query("MATCH (a:CollectionArtist) RETURN a")
    List<Artist> getAllArtists();

    @Query("MATCH (a:CollectionArtist) WHERE a.popularity = $popularity RETURN a")
    List<Artist> searchArtistsByPopularity(String popularity);

    @Query("MATCH (a:CollectionArtist) WHERE a.genre = $genre RETURN a")
    List<Artist> searchArtistsByGenre(String genre);

}
