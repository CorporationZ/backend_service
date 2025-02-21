package uz.salikhdev.backend_service.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.backend_service.dto.request.CreateStudentDto;
import uz.salikhdev.backend_service.service.StudentService;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody CreateStudentDto dto) {
        studentService.createStudent(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTeachers() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping("/add-course")
    public ResponseEntity<?> addStudentToCourse(@Validated @NonNull @RequestParam Long studentId, @Validated @NonNull @RequestParam Long courseId) {
        studentService.addStudentToCourse(studentId, courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}