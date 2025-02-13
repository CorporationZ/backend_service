package uz.salikhdev.backend_service.dto.request;

import lombok.Builder;

@Builder
public record CreateStudentDto(
        String name,
        String email
) {
}
