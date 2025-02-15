package uz.salikhdev.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
