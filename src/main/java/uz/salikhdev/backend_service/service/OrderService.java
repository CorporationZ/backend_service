package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.entity.Order;
import uz.salikhdev.backend_service.entity.Product;
import uz.salikhdev.backend_service.repository.OrderRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

    }


    public Order createOrder(Long customerId, Set<Long> productIds) {
        Customer customer = customerService.getCustomer(customerId);

        Set<Product> products = productIds.stream()
                .map(productService::getProduct)
                .collect(Collectors.toSet());

        Order order = Order.builder()
                .customer(customer)
                .products((List<Product>) products)
                .build();

        return orderRepository.save(order);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long id, Long customerId, Set<Long> productIds) {
        Order orderToUpdate = getOrderById(id);
        Customer customer = customerService.getCustomer(customerId);

        Set<Product> products = productIds.stream()
                .map(productService::getProduct)
                .collect(Collectors.toSet());

        Order updatedOrder = Order.builder()
                .id(orderToUpdate.getId())
                .customer(customer)
                .products((List<Product>) products)
                .build();

        return orderRepository.save(updatedOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
