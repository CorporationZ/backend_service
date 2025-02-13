package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.backend_service.dto.AccountCreateDto;
import uz.salikhdev.backend_service.dto.MessageDto;
import uz.salikhdev.backend_service.service.AccountService;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<MessageDto> createAccount(@RequestBody AccountCreateDto dto) {
     accountService.saveAccount(dto);
     return ResponseEntity.ok(new MessageDto("Account created",true));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @SneakyThrows
    @GetMapping("/id")
    public ResponseEntity<?> getAccountById(@RequestParam String id) {
        return ResponseEntity.ok(accountService.getAccountById(id));

    }
}
