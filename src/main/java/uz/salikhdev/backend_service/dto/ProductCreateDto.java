package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record ProductCreateDto(
        String name,
        double price,
        String categoryId
) {

}
