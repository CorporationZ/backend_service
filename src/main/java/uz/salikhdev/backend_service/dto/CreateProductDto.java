package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record CreateProductDto(
        Long productName,
        Double price,
        Long product_id
) {

}
