package uz.salikhdev.backend_service.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.backend_service.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByTitle(String title);

}