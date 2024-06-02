package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Album;

import java.util.List;

public interface IAlbumService {
    Album createAlbum(Album album);
    Album updateAlbum(String album_id, Album album);
    void deleteAlbum(String album_id);
    Album getAlbumById(String album_id);
    List<Album> getAllAlbums();
    List<Album> searchAlbumsByPopularity(String popularity);
    List<Album> searchAlbumsByGenre(String genre);
    List<Album> getAlbumsByArtist(String artistName);
}