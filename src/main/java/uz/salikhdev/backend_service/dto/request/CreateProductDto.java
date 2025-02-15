package uz.salikhdev.backend_service.dto.request;

import java.math.BigDecimal;

public record CreateProductDto(
        String name,
        BigDecimal price
) {
}
