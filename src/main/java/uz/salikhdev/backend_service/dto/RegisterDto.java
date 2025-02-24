package uz.salikhdev.backend_service.dto;


import lombok.Builder;

@Builder
public record RegisterDto(
        String email,
        String password
) {
}
