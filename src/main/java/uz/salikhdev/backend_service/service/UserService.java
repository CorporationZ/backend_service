package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.RegisterDto;
import uz.salikhdev.backend_service.dto.TokenDto;
import uz.salikhdev.backend_service.entitiy.User;
import uz.salikhdev.backend_service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void registerUser(RegisterDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }

    public TokenDto login(RegisterDto dto) {

        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }

        String token = jwtService.generateToken(user);

        return TokenDto.builder()
                .token(token)
                .expirationAt(jwtService.getExpirationTime())
                .build();
    }
}
