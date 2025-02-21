package uz.salikhdev.backend_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import uz.salikhdev.backend_service.dto.response.StudentDto;
import uz.salikhdev.backend_service.entity.Course;
import uz.salikhdev.backend_service.entity.Student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings({
        @Mapping(source = "courses", target = "courses", qualifiedByName = "coursesToStringList")
    })
    StudentDto studentToStudentDto(Student student);

    @Named("coursesToStringList")
    default List<String> coursesToStringList(Set<Course> courses) {
        return courses.stream()
                .map(Course::getTitle)  // Yoki boshqa biron bir maydonni tanlang
                .collect(Collectors.toList());
    }
}
