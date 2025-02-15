package uz.salikhdev.backend_service.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateProductDto(
        String name,
        BigDecimal price
) {
}
