package uz.salikhdev.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entity.Account;

public interface AccontRepository extends JpaRepository<Account , String> {
}
