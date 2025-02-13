package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record AccountCreateDto(
         String accountNumber,
         Double balance,
         String userId

) {
}
