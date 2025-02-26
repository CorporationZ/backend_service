package uz.salikhdev.backend_service.service;

import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.UserCreateDto;
import uz.salikhdev.backend_service.entity.AgeType;
import uz.salikhdev.backend_service.entity.AgeTypeRepository;
import uz.salikhdev.backend_service.entity.User;
import uz.salikhdev.backend_service.entity.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AgeTypeRepository ageTypeRepository;
    private UserRepository repository;

    public UserService(UserRepository userRepository, AgeTypeRepository ageTypeRepository) {
        this.userRepository = userRepository;
        this.ageTypeRepository = ageTypeRepository;
    }

    public void saveUser(UserCreateDto dto) {

        // check
        User user = User.builder()
                .fullName(dto.fullName())
                .age(dto.age())
                .build();

        AgeType ageType = null;

        if (dto.age() < 27) {
            ageType = ageTypeRepository.findById(1L).get();
        } else {
            ageType = ageTypeRepository.findById(2L).get();
        }

        user.setAgeType(ageType);

        userRepository.save(user);

    }

}
