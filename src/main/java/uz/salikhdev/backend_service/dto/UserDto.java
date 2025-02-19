package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record UserDto(
        Long id,
        String name,
        Integer phoneNumber
) {
}
