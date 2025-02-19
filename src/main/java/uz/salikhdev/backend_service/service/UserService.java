package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.UserCreateDto;
import uz.salikhdev.backend_service.dto.UserDto;
import uz.salikhdev.backend_service.enitiy.User;
import uz.salikhdev.backend_service.mapper.UserMapper;
import uz.salikhdev.backend_service.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void create(UserCreateDto userDto) {
        if (userRepository.existsByPhoneNumber(userDto.phoneNumber())) {
            throw new RuntimeException("User with this phone number already exists");
        }
        User user = userMapper.toCreateEntity(userDto);
        userRepository.save(user);
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        List<User> all = userRepository.findAll();
        return userMapper.toDtoList(all);
    }

}
