package uz.salikhdev.backend_service.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.backend_service.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
