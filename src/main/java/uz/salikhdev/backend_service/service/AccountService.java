package uz.salikhdev.backend_service.service;

import uz.salikhdev.backend_service.dto.AccountDTO;
import uz.salikhdev.backend_service.entity.Account;
import uz.salikhdev.backend_service.entity.My_user;
import uz.salikhdev.backend_service.repository.AccountRepository;
import uz.salikhdev.backend_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        My_user user = userRepository.findById(accountDTO.getUserId())
                                  .orElseThrow(() -> new RuntimeException("User not found"));
        Account account = Account.builder()
                                 .accountNumber(accountDTO.getAccountNumber())
                                 .balance(accountDTO.getBalance())
                                 .user(user)
                                 .build();
        Account savedAccount = accountRepository.save(account);
        return AccountDTO.builder()
                         .id(savedAccount.getId())
                         .accountNumber(savedAccount.getAccountNumber())
                         .balance(savedAccount.getBalance())
                         .userId(savedAccount.getUser().getId())
                         .build();
    }

    public AccountDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                                           .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountDTO.builder()
                         .id(account.getId())
                         .accountNumber(account.getAccountNumber())
                         .balance(account.getBalance())
                         .userId(account.getUser().getId())
                         .build();
    }
}
