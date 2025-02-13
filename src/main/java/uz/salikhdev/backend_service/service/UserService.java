package uz.salikhdev.backend_service.service;

import uz.salikhdev.backend_service.dto.UserDTO;
import uz.salikhdev.backend_service.entity.My_user;
import uz.salikhdev.backend_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        My_user user = My_user.builder()
                        .fullName(userDTO.getFullName())
                        .email(userDTO.getEmail())
                        .build();
        My_user savedUser = userRepository.save(user);
        return UserDTO.builder()
                      .id(savedUser.getId())
                      .fullName(savedUser.getFullName())
                      .email(savedUser.getEmail())
                      .build();
    }

    public UserDTO getUserById(Long userId) {
        My_user user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return UserDTO.builder()
                      .id(user.getId())
                      .fullName(user.getFullName())
                      .email(user.getEmail())
                      .build();
    }
}
