package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Album;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.AlbumRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IAlbumService;

import java.util.List;

@Service
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album createAlbum(Album album) {
        return albumRepository.createAlbum(album);
    }

    public Album updateAlbum(String albumId, Album album) {
        return albumRepository.updateAlbum(albumId, album);
    }

    public void deleteAlbum(String albumId) {
        albumRepository.deleteAlbum(albumId);
    }

    public Album getAlbumById(String albumId) {
        return albumRepository.getAlbumById(albumId);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }

    // Pretraga albuma po popularnosti
    public List<Album> searchAlbumsByPopularity(double popularity) {
        return albumRepository.searchAlbumsByPopularity(popularity);
    }

    // Pretraga albuma po žanru
    public List<Album> searchAlbumsByGenre(String genre) {
        return albumRepository.searchAlbumsByGenre(genre);
    }

    public List<Album> getAlbumsByArtist(String artistName) {
        return albumRepository.getAlbumsByArtist(artistName);
    }

}
