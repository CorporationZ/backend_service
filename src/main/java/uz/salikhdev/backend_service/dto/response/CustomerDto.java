package uz.salikhdev.backend_service.dto.response;

public record CustomerDto(
        Long id,
        String name,
        String email
) {
}
