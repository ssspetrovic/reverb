package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongPopularityProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MostPopularSongInPlaylistDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongTempoProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.HighEnergyMusicProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.LongestSongInEveryAlbumProjection;

import java.util.List;
import java.util.Map;

public interface ISongService {
    Song createSong(Song song);
    Song updateSong(String track_id, Song song);
    void deleteSong(String track_id);
    Song getSongById(String track_id);
    List<Song> getAllSongs();
    List<Song> getSongsByArtist(String artistName);
    List<Song> getSongsByAlbum(String albumName);
    List<Song> searchSongsByCriteria(SongSearchCriteriaDTO criteria);
    List<Song> searchSongsByPopularity(Integer popularity);
    List<Song> searchSongsByPlaylistGenre(String playlistGenre);
    List<Song> searchSongsByEnergy(String energy);
    List<Song> searchSongsByPlaylistSubgenre(String playlistSubgenre);
    List<SongPopularityProjection> updatePopularityBasedOnEnergy();
    List<SongTempoProjection> updateTempoBasedOnPopularity();
    List<MostPopularSongInPlaylistDTO> getMostPopularSongsFromEachPlaylist();
    List<HighEnergyMusicProjection> getHighEnergyMusicBasedOnGenre(String playlist_genre);
    List<LongestSongInEveryAlbumProjection> getLongestSongInEveryAlbum();
}
