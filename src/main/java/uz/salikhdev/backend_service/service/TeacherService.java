package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateTeacherDto;
import uz.salikhdev.backend_service.dto.response.TeacherDto;
import uz.salikhdev.backend_service.entity.Teacher;
import uz.salikhdev.backend_service.exception.EntityAlreadyExistsException;
import uz.salikhdev.backend_service.exception.EntityNotFound;
import uz.salikhdev.backend_service.repositroy.TeacherRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Teacher not found id : %s".formatted(id))
        );
    }

    public void createTeacher(CreateTeacherDto dto) {

        if (teacherRepository.existsByEmail(dto.emil())) {
            throw new EntityAlreadyExistsException("Teacher already exists");
        }

        var teacher = Teacher.builder()
                .name(dto.name())
                .email(dto.emil())
                .build();

        teacherRepository.save(teacher);
    }

    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(teacher -> {
                    return TeacherDto.builder()
                            .id(teacher.getId())
                            .name(teacher.getName())
                            .build();
                })
                //.filter(teacherDto -> teacherDto.name().startsWith("A"))
                .toList();
    }

}
