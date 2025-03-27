package uz.salikhdev.backend_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.backend_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}