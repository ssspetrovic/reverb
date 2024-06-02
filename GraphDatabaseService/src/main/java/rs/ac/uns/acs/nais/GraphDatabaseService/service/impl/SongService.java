package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.SongRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.SongSearchCriteriaDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.ISongService;

import java.util.List;

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
        return songRepository.createSong(song);
    }

    public Song updateSong(String trackId, Song song) {
        return songRepository.updateSong(trackId, song);
    }

    public void deleteSong(String trackId) {
        songRepository.deleteSong(trackId);
    }

    public Song getSongById(String trackId) {
        return songRepository.getSongById(trackId);
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

    // Pretraga pesama po popularnosti
    public List<Song> searchSongsByPopularity(Integer popularity) {
        return songRepository.searchSongsByPopularity(popularity);
    }

    // Pretraga pesama po žanru plejliste
    public List<Song> searchSongsByPlaylistGenre(String playlistGenre) {
        return songRepository.searchSongsByPlaylistGenre(playlistGenre);
    }

    // Pretraga pesama po energiji
    public List<Song> searchSongsByEnergy(String energy) {
        return songRepository.searchSongsByEnergy(energy);
    }

    // Pretraga pesama po subžanru plejliste
    public List<Song> searchSongsByPlaylistSubgenre(String playlistSubgenre) {
        return songRepository.searchSongsByPlaylistSubgenre(playlistSubgenre);
    }
}
