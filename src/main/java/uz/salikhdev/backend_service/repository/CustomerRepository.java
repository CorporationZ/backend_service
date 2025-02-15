package uz.salikhdev.backend_service.repository;

import uz.salikhdev.backend_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
