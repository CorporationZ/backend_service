package uz.salikhdev.backend_service.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import uz.salikhdev.backend_service.entity.Order;

import java.util.Optional;


@Repository
public class OrderRepository {
    private static final String KEY = "Order";
    private final RedisTemplate<String, Object> redisTemplate;

    public OrderRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(Order order) {
        redisTemplate.opsForHash().put(KEY, order.getId(), order);
    }

    public Optional<Order> findById(String id) {
        return Optional.ofNullable((Order) redisTemplate.opsForHash().get(KEY, id));
    }

    public void deleteById(String id) {
        redisTemplate.opsForHash().delete(KEY, id);
    }


}
