package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Album;

import java.util.List;

public interface IAlbumService {
    Album createAlbum(Album album);
    Album updateAlbum(String albumId, Album album);
    void deleteAlbum(String albumId);
    Album getAlbumById(String albumId);
    List<Album> getAllAlbums();
    List<Album> searchAlbumsByPopularity(double popularity);
    List<Album> searchAlbumsByGenre(String genre);
    List<Album> getAlbumsByArtist(String artistName);
}
