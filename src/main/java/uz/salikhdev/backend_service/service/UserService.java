package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.entity.User;
import uz.salikhdev.backend_service.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new RuntimeException("User not found id" + id)
        );

    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(String id, User user) {
        User userToUpdate = getUserById(id);
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setFullName(user.getFullName());
        userRepository.save(userToUpdate);
    }

}
