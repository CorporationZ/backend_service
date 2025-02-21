package uz.salikhdev.backend_service.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record StudentDto(
        Long id,
        String name,
        String email,
        List<String> courses
) {
}