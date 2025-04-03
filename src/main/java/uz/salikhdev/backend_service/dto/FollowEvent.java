package uz.salikhdev.backend_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowEvent {
    private Long followerId;
    private Long followedId;
    private LocalDateTime eventTime;

}
