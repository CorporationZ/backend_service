package uz.salikhdev.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}