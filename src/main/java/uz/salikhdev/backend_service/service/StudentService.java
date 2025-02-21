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
import uz.salikhdev.backend_service.repository.StudentRepository;
import uz.salikhdev.backend_service.mapper.StudentMapper;
import uz.salikhdev.backend_service.mapper.CreateStudentMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final StudentMapper studentMapper;  // StudentDto uchun mapper
    private final CreateStudentMapper createStudentMapper;  // CreateStudentDto uchun mapper

    public void createStudent(CreateStudentDto dto) {
        if (studentRepository.existsByEmail(dto.email())) {
            log.error("Student with email {} already exists", dto.email());
            throw new EntityAlreadyExistsException("Student with email " + dto.email() + " already exists");
        }

        // CreateStudentDto dan Student'ga o'tish
        Student student = createStudentMapper.createStudentDtoToStudent(dto);

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

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(s -> studentMapper.studentToStudentDto(s))  // Student obyektlarini StudentDto'ga o'zgartirish
                .toList();
    }
}
