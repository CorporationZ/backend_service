package uz.salikhdev.backend_service.dto.request;

import lombok.Builder;

import java.util.Set;

@Builder
public record CreateOrderDto(
        Long customerId,
        Set<Long> productIds
) {
}
