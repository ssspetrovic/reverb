package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends Neo4jRepository<Artist, Long> {

    @Query("CREATE (a:CollectionArtist {name: $a.name}) SET a.artist_id = id(a)")
    Artist createArtist(Artist artist);

    @Query("MATCH (a:CollectionArtist {a.id: $artist.artist_id}) SET a.name = $artist.name RETURN a")
    Artist updateArtist(Artist artist);

    @Query("MATCH (a:CollectionArtist {artist_id: $artist_id}) DELETE a")
    void deleteArtist(Long artist_id);

    @Query("MATCH (a:CollectionArtist {artist_id: $artist_id}) RETURN a LIMIT 1")
    Artist getArtistById(Long artist_id);

    @Query("MATCH (a:CollectionArtist {name: $name}) RETURN a LIMIT 1")
    Artist getArtistByName(String name);

    @Query("MATCH (a:CollectionArtist) RETURN a")
    List<Artist> getAllArtists();

    @Query("MATCH (a:CollectionArtist) WHERE a.popularity = $popularity RETURN a")
    List<Artist> searchArtistsByPopularity(String popularity);

    @Query("MATCH (a:CollectionArtist) WHERE a.genre = $genre RETURN a")
    List<Artist> searchArtistsByGenre(String genre);

}
