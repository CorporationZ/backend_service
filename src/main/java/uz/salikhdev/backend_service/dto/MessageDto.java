package uz.salikhdev.backend_service.dto;

import lombok.Builder;

@Builder
public record MessageDto(String message, Boolean status) {
}
