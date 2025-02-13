package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateCourseDto;
import uz.salikhdev.backend_service.dto.response.CourseDto;
import uz.salikhdev.backend_service.entity.Course;
import uz.salikhdev.backend_service.entity.Teacher;
import uz.salikhdev.backend_service.exception.EntityAlreadyExistsException;
import uz.salikhdev.backend_service.exception.EntityNotFound;
import uz.salikhdev.backend_service.repositroy.CourseRepository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherService teacherService;

    public void createCourse(CreateCourseDto dto) {

        if (courseRepository.existsByTitle(dto.title())) {
            log.error("Course with title {} already exists", dto.title());
            throw new EntityAlreadyExistsException("Course with title " + dto.title() + " already exists");
        }

        Teacher teacher = teacherService.getTeacher(dto.teacherId());

        var course = Course.builder()
                .title(dto.title())
                .teacher(teacher)
                .price(BigDecimal.valueOf(dto.price()))
                .build();

        courseRepository.save(course);
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Course not found id : %s".formatted(id))
        );
    }

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(c -> {
                    return CourseDto.builder()
                            .id(c.getId())
                            .title(c.getTitle())
                            .price(c.getPrice().doubleValue())
                            .teacherName(c.getTeacher().getName())
                            .build();
                })
                .toList();
    }
}
