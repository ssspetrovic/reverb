package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;

import java.util.List;

public interface ISongService {
    Song createSong(Song song);
    Song updateSong(String trackId, Song song);
    void deleteSong(String trackId);
    Song getSongById(String trackId);
    List<Song> getAllSongs();
    List<Song> getSongsByArtist(String artistName);
    List<Song> getSongsByAlbum(String albumName);
    List<Song> searchSongsByCriteria(SongSearchCriteriaDTO criteria);
    List<Song> searchSongsByPopularity(Integer popularity);
    List<Song> searchSongsByPlaylistGenre(String playlistGenre);
    List<Song> searchSongsByEnergy(Integer energy);
    List<Song> searchSongsByPlaylistSubgenre(String playlistSubgenre);
}
