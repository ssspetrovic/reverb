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

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.createAlbum(album);
    }

    @Override
    public Album updateAlbum(String album_id, Album album) {
        album.setAlbumId(album_id);
        return albumRepository.updateAlbum(album);
    }

    @Override
    public void deleteAlbum(String album_id) {
        albumRepository.deleteAlbum(album_id);
    }

    @Override
    public Album getAlbumById(String album_id) {
        return albumRepository.getAlbumById(album_id);
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.getAllAlbums();
    }

    @Override
    public List<Album> searchAlbumsByPopularity(String popularity) {
        return albumRepository.searchAlbumsByPopularity(popularity);
    }

    @Override
    public List<Album> searchAlbumsByGenre(String genre) {
        return albumRepository.searchAlbumsByGenre(genre);
    }

    @Override
    public List<Album> getAlbumsByArtist(String name) {
        return albumRepository.getAlbumsByArtist(name);
    }
}