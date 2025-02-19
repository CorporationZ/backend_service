package uz.salikhdev.backend_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.salikhdev.backend_service.dto.UserCreateDto;
import uz.salikhdev.backend_service.dto.UserDto;
import uz.salikhdev.backend_service.enitiy.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", source = "firstName")
    UserDto toDto(User entity);

    @Mapping(target = "firstName", source = "name")
    User toCreateEntity(UserCreateDto dto);

    List<UserDto> toDtoList(List<User> entityList);

}
