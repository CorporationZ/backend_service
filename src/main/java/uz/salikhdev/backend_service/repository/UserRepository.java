package uz.salikhdev.backend_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
