package uz.salikhdev.backend_service.dto.request;

import lombok.Builder;

@Builder
public record CreateCourseDto(
        String title,
        Double price,
        Long teacherId
) {
}