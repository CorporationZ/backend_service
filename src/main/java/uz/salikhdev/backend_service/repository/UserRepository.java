package uz.salikhdev.backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.salikhdev.backend_service.entity.My_user;

public interface UserRepository extends JpaRepository<My_user, Long> {
}