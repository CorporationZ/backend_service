package uz.salikhdev.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
