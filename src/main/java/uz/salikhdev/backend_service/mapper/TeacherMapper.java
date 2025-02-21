package uz.salikhdev.backend_service.mapper;

import org.mapstruct.Mapper;
import uz.salikhdev.backend_service.dto.response.TeacherDto;
import uz.salikhdev.backend_service.entity.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDto teacherToTeacherDto(Teacher teacher);  // Teacher'dan TeacherDto'ga o'tkazish
}
