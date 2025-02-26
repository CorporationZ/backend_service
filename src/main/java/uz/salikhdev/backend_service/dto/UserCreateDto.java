package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record UserCreateDto(
        String fullName,
        Integer age
        ) {

}
