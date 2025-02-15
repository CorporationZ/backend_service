package uz.salikhdev.backend_service.dto.request.respons;
import lombok.Builder;

import java.util.List;

@Builder


public record OrderDTO(
        Long id,
        Long customerId,
        List<Long> products
) {

}

