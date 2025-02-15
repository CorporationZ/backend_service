package uz.salikhdev.backend_service.dto.request.request;

import lombok.Builder;

@Builder
public record CreateCustomerDto(
        String name,
        String email,
        Long customer_id
) {
}
