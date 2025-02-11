package uz.salikhdev.backend_service.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entitiy.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}