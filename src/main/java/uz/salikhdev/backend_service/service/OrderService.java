package uz.salikhdev.backend_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.entity.Order;
import uz.salikhdev.backend_service.repository.OrderRepository;

import java.util.Optional;

@Service
public class OrderService{
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Order> kafkaTemplate;
    private static final String TOPIC = "order-topic";

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, Order> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
        kafkaTemplate.send(TOPIC, order);
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

}
