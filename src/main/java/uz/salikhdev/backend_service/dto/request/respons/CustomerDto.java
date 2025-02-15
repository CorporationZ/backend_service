package uz.salikhdev.backend_service.dto.request.respons;

import lombok.Builder;

@Builder
public record CustomerDto(
        Long id,
        String name,
        String email
) {
}
