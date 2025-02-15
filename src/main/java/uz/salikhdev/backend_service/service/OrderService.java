package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.entity.Order;
import uz.salikhdev.backend_service.entity.Product;
import uz.salikhdev.backend_service.repository.CustomerRepository;
import uz.salikhdev.backend_service.repository.OrderRepository;
import uz.salikhdev.backend_service.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public Order createOrder(Long customerId, List<Long> productIds) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Product> products = productRepository.findAllById(productIds);

        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(products);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

