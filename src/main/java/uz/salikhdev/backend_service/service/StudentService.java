package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateStudentDto;
import uz.salikhdev.backend_service.dto.response.StudentDto;
import uz.salikhdev.backend_service.entity.Course;
import uz.salikhdev.backend_service.entity.Student;
import uz.salikhdev.backend_service.exception.EntityAlreadyExistsException;
import uz.salikhdev.backend_service.exception.EntityNotFound;
import uz.salikhdev.backend_service.repositroy.StudentRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;
    private final CourseService courseService;


    public void createStudent(CreateStudentDto dto) {

        if (studentRepository.existsByEmail(dto.email())) {
            log.error("Student with email {} already exists", dto.email());
            throw new EntityAlreadyExistsException("Student with email " + dto.email() + " already exists");
        }

        Student student = Student.builder()
                .name(dto.name())
                .email(dto.email())
                .build();

        studentRepository.save(student);
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Student not found id : %s".formatted(id))
        );
    }

    public void addStudentToCourse(Long studentId, Long courseId) {
        var course = courseService.getCourse(courseId);
        var student = getStudent(studentId);
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public List<StudentDto>  getAllStudents() {
        return studentRepository.findAll().stream()
                .map(s -> {
                    return StudentDto.builder()
                            .id(s.getId())
                            .name(s.getName())
                            .email(s.getEmail())
                            .courses(s.getCourses().stream().map(Course::getTitle).toList())
                            .build();
                })
                .toList();
    }


}
