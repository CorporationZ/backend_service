package uz.salikhdev.backend_service.dto.request;

public record CreateOrderDto(
        Long customer_id,
        Long product_id
) {
}
