package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.backend_service.dto.request.CreateCourseDto;
import uz.salikhdev.backend_service.service.CourseService;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody CreateCourseDto dto) {
        courseService.createCourse(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTeachers() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

}
