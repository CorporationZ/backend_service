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
import uz.salikhdev.backend_service.mapper.CourseMapper;
import uz.salikhdev.backend_service.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final CourseMapper courseMapper;

    public void createCourse(CreateCourseDto dto) {
        if (courseRepository.existsByTitle(dto.title())) {
            log.error("Course with title {} already exists", dto.title());
            throw new EntityAlreadyExistsException("Course with title " + dto.title() + " already exists");
        }

        Teacher teacher = teacherService.getTeacher(dto.teacherId());
        Course course = courseMapper.createCourseDtoToCourse(dto);  // Mapping CreateCourseDto to Course
        course.setTeacher(teacher);

        courseRepository.save(course);
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Course not found id : %s".formatted(id))
        );
    }
   

    public List<Object> getAllCourses() {
        return courseRepository.findAll().stream()  // courseRepository'dan barcha Course ob'ektlari olinadi
                .map(course -> courseMapper.courseToCourseDto(course))  // Har bir Course ob'ekti CourseDto'ga o'zgartiriladi
                .collect(Collectors.toList());  // Natija List<CourseDto> bo'ladi
    }

//    public List<CourseDto> getAllCourses() {
//        return courseRepository.findAll().stream()
//                .map(courseMapper::courseToCourseDto)  // Mapping Course to CourseDto
//                .toList();
   // }
}
