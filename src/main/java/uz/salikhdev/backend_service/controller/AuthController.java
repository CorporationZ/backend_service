package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.salikhdev.backend_service.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(
            @RequestPart("username") String username,
            @RequestPart("password") String password,
            @RequestPart("image") MultipartFile image) {
        authService.register(username, password, image);
        return ResponseEntity.ok().body("Success!!!!");
    }

}
