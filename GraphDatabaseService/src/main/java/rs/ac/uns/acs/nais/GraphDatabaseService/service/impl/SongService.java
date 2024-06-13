package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.SongRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongPopularityProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ISongService;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MostPopularSongInPlaylistDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongTempoProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.HighEnergyMusicProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.LongestSongInEveryAlbumProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PerformedByProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.IncludedInProjection;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.IncludedInPlaylistProjection;

import java.util.List;
import java.util.Map;

@Service
public class SongService implements ISongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song createSong(Song song) {
        if (song == null || song.getTrackId() == null || song.getTrackName() == null) {
            throw new IllegalArgumentException("Invalid song data");
        }
        return songRepository.createSong(song.getTrackId(),
                                        song.getTrackName(),
                                        song.getTrackArtist(),
                                        song.getTrackPopularity(),
                                        song.getTrackAlbumId(),
                                        song.getTrackAlbumName(),
                                        song.getTrackAlbumReleaseDate(),
                                        song.getPlaylistName(),
                                        song.getPlaylistId(),
                                        song.getPlaylistGenre(),
                                        song.getPlaylistSubgenre(),
                                        song.getDanceability(),
                                        song.getEnergy(),
                                        song.getKey(),
                                        song.getLoudness(),
                                        song.getMode(),
                                        song.getSpeechiness(),
                                        song.getAcousticness(),
                                        song.getInstrumentalness(),
                                        song.getLiveness(),
                                        song.getValence(),
                                        song.getTempo(),
                                        song.getDurationMs());
    }

    public Song updateSong(String track_id, Song song) {
        song.setTrackId(track_id);
        return songRepository.updateSong(song.getTrackId(),
                                        song.getTrackName(),
                                        song.getTrackArtist(),
                                        song.getTrackPopularity(),
                                        song.getTrackAlbumId(),
                                        song.getTrackAlbumName(),
                                        song.getTrackAlbumReleaseDate(),
                                        song.getPlaylistName(),
                                        song.getPlaylistId(),
                                        song.getPlaylistGenre(),
                                        song.getPlaylistSubgenre(),
                                        song.getDanceability(),
                                        song.getEnergy(),
                                        song.getKey(),
                                        song.getLoudness(),
                                        song.getMode(),
                                        song.getSpeechiness(),
                                        song.getAcousticness(),
                                        song.getInstrumentalness(),
                                        song.getLiveness(),
                                        song.getValence(),
                                        song.getTempo(),
                                        song.getDurationMs());
    }

    public void deleteSong(String track_id) {
        songRepository.deleteSong(track_id);
    }

    public Song getSongById(String track_id) {
        return songRepository.getSongById(track_id);
    }

    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }

    public List<Song> getSongsByArtist(String artistName) {
        return songRepository.getSongsByArtist(artistName);
    }

    public List<Song> getSongsByAlbum(String albumName) {
        return songRepository.getSongsByAlbum(albumName);
    }

    public List<Song> searchSongsByCriteria(SongSearchCriteriaDTO criteria) {
        if (criteria == null) {
            throw new IllegalArgumentException("Criteria cannot be null");
        }

        if (criteria.getPlaylistSubgenre() != null) {
            return songRepository.searchSongsByPlaylistSubgenre(criteria.getPlaylistSubgenre());
        }
        if (criteria.getEnergy() != null) {
            return songRepository.searchSongsByEnergy(criteria.getEnergy());
        } else {
            throw new IllegalArgumentException("Invalid search criteria");
        }
    }

    public List<Song> searchSongsByPopularity(Integer popularity) {
        return songRepository.searchSongsByPopularity(popularity);
    }

    public List<Song> searchSongsByPlaylistGenre(String playlistGenre) {
        return songRepository.searchSongsByPlaylistGenre(playlistGenre);
    }

    public List<Song> searchSongsByEnergy(String energy) {
        return songRepository.searchSongsByEnergy(energy);
    }

    public List<Song> searchSongsByPlaylistSubgenre(String playlistSubgenre) {
        return songRepository.searchSongsByPlaylistSubgenre(playlistSubgenre);
    }

    public List<SongPopularityProjection> updatePopularityBasedOnEnergy() {
        return songRepository.updatePopularityBasedOnEnergy();
    }

    public List<SongTempoProjection> updateTempoBasedOnPopularity() {
        return songRepository.updateTempoBasedOnPopularity();
    }

    public List<MostPopularSongInPlaylistDTO> getMostPopularSongsFromEachPlaylist() {
        return songRepository.getMostPopularSongsFromEachPlaylist();
    }

    public List<HighEnergyMusicProjection> getHighEnergyMusicBasedOnGenre(String playlist_genre) {
        return songRepository.getHighEnergyMusicBasedOnGenre(playlist_genre);
    }

    public List<LongestSongInEveryAlbumProjection> getLongestSongInEveryAlbum() {
        return songRepository.getLongestSongInEveryAlbum();
    }

    public void createPerformedByRelationship(String trackId, String name) {
        songRepository.createPerformedByRelationship(trackId, name);
    }

    public void createIncludedInRelationship(String trackId, String albumName) {
        songRepository.createIncludedInRelationship(trackId, albumName);
    }

    public void createIncludedInPlaylistRelationship(String trackId, String playlistId) {
        songRepository.createIncludedInPlaylistRelationship(trackId, playlistId);
    }

    public List<PerformedByProjection> getAllPerformedByRelationships() {
        return songRepository.getAllPerformedByRelationships();
    }

    public List<IncludedInProjection> getAllIncludedInRelationships() {
        return songRepository.getAllIncludedInRelationships();
    }

    public List<IncludedInPlaylistProjection> getAllIncludedInPlaylistRelationships() {
        return songRepository.getAllIncludedInPlaylistRelationships();
    }

    public void updatePerformedByRelationship(String trackId, String name) {
        songRepository.updatePerformedByRelationship(trackId, name);
    }

    public void updateIncludedInRelationship(String trackId, String albumName) {
        songRepository.updateIncludedInRelationship(trackId, albumName);
    }

    public void updateIncludedInPlaylistRelationship(String trackId, String playlistId) {
        songRepository.updateIncludedInPlaylistRelationship(trackId, playlistId);
    }

    public void deletePerformedByRelationship(String trackId) {
        songRepository.deletePerformedByRelationship(trackId);
    }

    public void deleteIncludedInRelationship(String trackId) {
        songRepository.deleteIncludedInRelationship(trackId);
    }

    public void deleteIncludedInPlaylistRelationship(String trackId) {
        songRepository.deleteIncludedInPlaylistRelationship(trackId);
    }

    public List<Song> recommendSongs(String genre, String subgenre, String artist) {
        return songRepository.recommendSongs(genre, subgenre, artist);
    }

    public List<String> getTop50Songs() {
        return songRepository.getTop50Songs();
    }

}
