package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.AccountCreateDto;
import uz.salikhdev.backend_service.entity.Account;
import uz.salikhdev.backend_service.repository.AccontRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {

    private final AccontRepository accontRepository;
    private final UserService userService;

    public List<Account> getAllAccounts() {
        return accontRepository.findAll();
    }

    public void saveAccount(AccountCreateDto dto) {

        var user = userService.getUserById(dto.userId());

        Account account = Account.builder()
                .accountNumber(dto.accountNumber())
                .balance(0.0)
                .user(user)
                .build();

        accontRepository.save(account);
    }

    public Account getAccountById(String accountId) {
        return accontRepository.findById(accountId)
                .orElseThrow(
                        () -> new RuntimeException("Account not found")
                );
    }

    public void deleteAccount(String accountId) {
        accontRepository.deleteById(accountId);
    }
}
