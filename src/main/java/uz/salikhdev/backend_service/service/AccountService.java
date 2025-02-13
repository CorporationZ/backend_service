package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.MessegeDto;
import uz.salikhdev.backend_service.dto.AccountCreateDto;
import uz.salikhdev.backend_service.entity.Account;
import uz.salikhdev.backend_service.entity.User;
import uz.salikhdev.backend_service.repository.AccontRepository;

import javax.security.auth.login.AccountNotFoundException;
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

        User user = (User) userService.getUserById(dto.userId());

        Account account = Account.builder()
                .accountNumber()
                .balance()
                .user(user)
                .build();

    }
    public Account getAccoundById(String accountId) throws AccountNotFoundException {
        return accontRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }
    public void deleteAccount(String accountId) {
        accontRepository.deleteById(accountId);
    }

}
