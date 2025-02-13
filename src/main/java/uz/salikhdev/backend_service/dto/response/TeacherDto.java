package uz.salikhdev.backend_service.dto.response;

import lombok.Builder;

@Builder
public record TeacherDto(
        Long id,
        String name
) {
}
