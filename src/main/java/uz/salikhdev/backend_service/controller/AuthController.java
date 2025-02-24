package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.backend_service.dto.RegisterDto;
import uz.salikhdev.backend_service.dto.TokenDto;
import uz.salikhdev.backend_service.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok("Register is success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterDto dto) {
        TokenDto token = userService.login(dto);
        return ResponseEntity.ok(token);
    }

}
