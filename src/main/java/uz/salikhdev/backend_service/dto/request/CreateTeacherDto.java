package uz.salikhdev.backend_service.dto.request;

import lombok.Builder;

@Builder
public record CreateTeacherDto(
        String name,
        String emil
) {
}
