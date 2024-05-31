package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends Neo4jRepository<Artist, Long> {

    @Query("CREATE (a:CollectionArtist $props) RETURN a")
    Artist createArtist(Artist artist);

    @Query("MATCH (a:CollectionArtist {artist_id: $artistId}) SET a = $props RETURN a")
    Artist updateArtist(String artistId, Artist artist);

    @Query("MATCH (a:CollectionArtist {artist_id: $artistId}) DELETE a")
    void deleteArtist(String artistId);

    @Query("MATCH (a:CollectionArtist {artist_id: $artistId}) RETURN a")
    Artist getArtistById(String artistId);

    @Query("MATCH (a:CollectionArtist) RETURN a")
    List<Artist> getAllArtists();

    @Query("MATCH (a:CollectionArtist) WHERE a.popularity = $popularity RETURN a")
    List<Artist> searchArtistsByPopularity(double popularity);

    @Query("MATCH (a:CollectionArtist) WHERE a.genre = $genre RETURN a")
    List<Artist> searchArtistsByGenre(String genre);

}
