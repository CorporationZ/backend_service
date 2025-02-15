package uz.salikhdev.backend_service.dto.response;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String name,
        BigDecimal price
) {
}
