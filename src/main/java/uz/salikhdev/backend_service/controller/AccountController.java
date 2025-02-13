package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.backend_service.dto.MessegeDto;
import uz.salikhdev.backend_service.service.AccountService;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<MessegeDto> createAccount(@RequestBody MessegeDto dto) {
     accountService.saveAccount(dto);
     return ResponseEntity.ok(new MessegeDto("Account created",true));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @SneakyThrows
    @GetMapping("/id")
    public ResponseEntity<?> getAccountById(@RequestParam String id) {
        return ResponseEntity.ok(accountService.getAccoundById(id));

    }
}
