package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Song;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.User;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.UserRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("Invalid user data");
        }
        return userRepository.createUser(user.getUserId());
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User updateUser(User user) {
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("Invalid user data");
        }
        return userRepository.updateUser(user.getUserId(), user.getFavoriteSongs());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public List<Song> getAllFavoriteSongs(Long userId) {
        Optional<User> user = getUserById(userId);
        return user.map(User::getFavoriteSongs).orElse(null);
    }
}
