package uz.salikhdev.backend_service.dto.response;

import java.util.List;

public record OrderDto(
        Long orderId,
        String customerName,
        List<String> productNames
) {
}
