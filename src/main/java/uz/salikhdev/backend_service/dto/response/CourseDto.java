package uz.salikhdev.backend_service.dto.response;

import lombok.Builder;

@Builder
public record CourseDto(
        Long id,
        String title,
        Double price,
        String teacherName
) {
}