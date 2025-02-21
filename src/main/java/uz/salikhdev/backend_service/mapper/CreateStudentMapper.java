package uz.salikhdev.backend_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.backend_service.dto.request.CreateStudentDto;
import uz.salikhdev.backend_service.entity.Student;

@Mapper(componentModel = "spring")
public interface CreateStudentMapper {
    @Mapping(target = "id", ignore = true)  // 'id' maydonini e'tiborsiz qoldiramiz
    @Mapping(target = "courses", ignore = true)  // 'courses' maydonini ham e'tiborsiz qoldiramiz
    Student createStudentDtoToStudent(CreateStudentDto dto);
}
