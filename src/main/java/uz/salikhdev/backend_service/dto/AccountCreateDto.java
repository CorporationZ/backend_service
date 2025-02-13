package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record AccountCreateDto(
        String userId,
        String accountNumber
) {
}
