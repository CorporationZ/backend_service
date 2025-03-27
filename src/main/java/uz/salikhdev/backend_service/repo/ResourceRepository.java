package uz.salikhdev.backend_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.backend_service.entity.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}