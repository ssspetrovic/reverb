package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Album;

import java.util.List;


@Repository
public interface AlbumRepository extends Neo4jRepository<Album, Long> {

    @Query("CREATE (al:CollectionAlbum {album_id: $album.album_id, name: $album.name}) RETURN al")
    Album createAlbum(Album album);

    @Query("MATCH (al:CollectionAlbum {album_id: $album.album_id}) SET al.name = $album.name RETURN al")
    Album updateAlbum(Album album);

    @Query("MATCH (al:CollectionAlbum {album_id: $album_id}) DELETE al")
    void deleteAlbum(String album_id);

    @Query("MATCH (al:CollectionAlbum {album_id: $album_id}) RETURN al LIMIT 1")
    Album getAlbumById(String album_id);

    @Query("MATCH (al:CollectionAlbum) RETURN al")
    List<Album> getAllAlbums();

    @Query("MATCH (al:CollectionAlbum)-[:INCLUDED_IN]->(s:Song)-[:PERFORMED_BY]->(a:CollectionArtist {name: $name}) RETURN al")
    List<Album> getAlbumsByArtist(String name);

    @Query("MATCH (al:CollectionAlbum) WHERE al.popularity = $popularity RETURN al")
    List<Album> searchAlbumsByPopularity(String popularity);

    @Query("MATCH (al:CollectionAlbum) WHERE al.genre = $genre RETURN al")
    List<Album> searchAlbumsByGenre(String genre);
}