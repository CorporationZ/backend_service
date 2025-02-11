package uz.salikhdev.backend_service.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entitiy.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}