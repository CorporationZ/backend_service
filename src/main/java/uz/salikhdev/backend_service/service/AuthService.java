package uz.salikhdev.backend_service.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.salikhdev.backend_service.entity.Resource;
import uz.salikhdev.backend_service.entity.User;
import uz.salikhdev.backend_service.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final S3Service s3Service;

    @Transactional
    public void register(String username, String password, MultipartFile image) {

        Resource resource = s3Service.uploadFile(image, "user-avatar");

        User user = User.builder()
                .username(username)
                .password(password)
                .image(resource.getKey())
                .resource(resource)
                .build();

        userRepository.save(user);
    }
}
