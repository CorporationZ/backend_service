package uz.salikhdev.backend_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.backend_service.dto.request.CreateCourseDto;
import uz.salikhdev.backend_service.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "id", ignore = true)  // 'id' maydonini e'tiborsiz qoldiramiz
    @Mapping(target = "students", ignore = true)  // 'students' maydonini e'tiborsiz qoldiramiz
    @Mapping(target = "teacher", ignore = true)  // 'teacher' maydonini e'tiborsiz qoldiramiz
    Course createCourseDtoToCourse(CreateCourseDto dto);

    Object courseToCourseDto(Course course);
}
