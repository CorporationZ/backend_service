package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.backend_service.dto.UserCreateDto;
import uz.salikhdev.backend_service.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateDto user) {
        userService.create(user);
        return ResponseEntity.ok().body("Create is success");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}
