package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;

import java.util.List;

@Repository
public interface SongRepository extends Neo4jRepository<Song, Long> {

    @Query("CREATE (s:CollectionSong $props) RETURN s")
    Song createSong(Song song);

    @Query("MATCH (s:CollectionSong {track_id: $trackId}) SET s = $props RETURN s")
    Song updateSong(String trackId, Song song);

    @Query("MATCH (s:CollectionSong {track_id: $trackId}) DELETE s")
    void deleteSong(String trackId);

    @Query("MATCH (s:CollectionSong {track_id: $trackId}) RETURN s")
    Song getSongById(String trackId);

    @Query("MATCH (s:Song) RETURN s")
    List<Song> getAllSongs();

    @Query("MATCH (s:Song)-[:PERFORMED_BY]->(a:CollectionArtist {name: $artistName}) RETURN s")
    List<Song> getSongsByArtist(String artistName);

    @Query("MATCH (s:Song)-[:INCLUDED_IN]->(al:CollectionAlbum {album_name: $albumName}) RETURN s")
    List<Song> getSongsByAlbum(String albumName);

    @Query("MATCH (s:Song) WHERE $criteria RETURN s")
    List<Song> searchSongsByCriteria(SongSearchCriteriaDTO criteria);

    @Query("MATCH (s:Song) WHERE s.track_popularity >= $popularity RETURN s")
    List<Song> searchSongsByPopularity(int popularity);

    @Query("MATCH (s:Song) WHERE s.playlist_genre = $playlistGenre RETURN s")
    List<Song> searchSongsByPlaylistGenre(String playlistGenre);

    @Query("MATCH (s:Song) WHERE s.energy = $energy RETURN s")
    List<Song> searchSongsByEnergy(double energy);

    @Query("MATCH (s:Song) WHERE s.playlist_subgenre = $playlistSubgenre RETURN s")
    List<Song> searchSongsByPlaylistSubgenre(String playlistSubgenre);
}
