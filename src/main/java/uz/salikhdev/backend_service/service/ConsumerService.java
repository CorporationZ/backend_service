package uz.salikhdev.backend_service.service;

port lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import uz.salikhdev.kafka_consumer.dto.UserDto;
import uz.salikhdev.kafka_consumer.event.FollowEvent;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final NotificationService notificationService;
    private final UserService userService;

    @KafkaListener(topics = "${spring.kafka.topics.follow-topic}", properties = "spring.json.value.default.type=uz.salikhdev.kafka_consumer.event.FollowEvent"
    )
    public void listenFollowEvent(FollowEvent followEvent) {

        UserDto fr = userService.getUserById(followEvent.getFollowerId());
        UserDto fd = userService.getUserById(followEvent.getFollowedId());

        String message = "User " + fr.name() + " followed you at " + followEvent.getEventTime();

        notificationService.sendNotification(fd.phoneNumber(), message);

    }

}
