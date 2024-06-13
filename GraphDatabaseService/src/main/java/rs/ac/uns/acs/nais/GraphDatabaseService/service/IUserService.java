package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import reactor.core.publisher.Mono;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);
    Optional<User> getUserById(Long userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long userId);
    List<String> getAllFavoriteSongs(Long userId);
    boolean hasFavoriteSong(Long userId, String trackId);
}
