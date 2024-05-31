package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Album;

import java.util.List;

@Repository
public interface AlbumRepository extends Neo4jRepository<Album, Long> {

    @Query("CREATE (al:CollectionAlbum $props) RETURN al")
    Album createAlbum(Album album);

    @Query("MATCH (al:CollectionAlbum {album_id: $albumId}) SET al = $props RETURN al")
    Album updateAlbum(String albumId, Album album);

    @Query("MATCH (al:CollectionAlbum {album_id: $albumId}) DELETE al")
    void deleteAlbum(String albumId);

    @Query("MATCH (al:CollectionAlbum {album_id: $albumId}) RETURN al")
    Album getAlbumById(String albumId);

    @Query("MATCH (al:CollectionAlbum) RETURN al")
    List<Album> getAllAlbums();

    @Query("MATCH (al:CollectionAlbum)-[:INCLUDED_IN]->(s:Song)-[:PERFORMED_BY]->(a:CollectionArtist {name: $artistName}) RETURN al")
    List<Album> getAlbumsByArtist(String artistName);

    @Query("MATCH (al:CollectionAlbum) WHERE al.popularity = $popularity RETURN al")
    List<Album> searchAlbumsByPopularity(double popularity);

    @Query("MATCH (al:CollectionAlbum) WHERE al.genre = $genre RETURN al")
    List<Album> searchAlbumsByGenre(String genre);
}
