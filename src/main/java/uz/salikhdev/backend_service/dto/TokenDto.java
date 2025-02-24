package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record TokenDto(
        String token,
        long expirationAt
) {
}
