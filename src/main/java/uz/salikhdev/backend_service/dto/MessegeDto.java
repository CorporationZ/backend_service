package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record MessegeDto(String message, Boolean status) {
}
