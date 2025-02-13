package uz.salikhdev.backend_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private Double balance;
    private Long userId; //foreign key
}
