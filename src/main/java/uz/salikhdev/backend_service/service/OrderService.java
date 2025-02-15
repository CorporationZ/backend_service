package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateCustomerDto;
import uz.salikhdev.backend_service.dto.request.CreateOrderDto;
import uz.salikhdev.backend_service.dto.response.OrderDto;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.entity.Order;
import uz.salikhdev.backend_service.entity.Product;
import uz.salikhdev.backend_service.exception.EntityNotFound;
import uz.salikhdev.backend_service.repositroy.CustomerRepository;
import uz.salikhdev.backend_service.repositroy.OrderRepository;
import uz.salikhdev.backend_service.repositroy.ProductRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ProductService productService;
    private final CustomerService customerService;


    public Order getOrder(Long id){
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Customer not found id : %s".formatted(id))
        );
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private OrderDto convertToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCustomer().getName(),
                order.getProducts().stream()
                        .map(product -> product.getName())
                        .collect(Collectors.toList())
        );
    }

    public OrderDto createOrder(Long customerId, Long productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.getProducts().add(product);
        order = orderRepository.save(order);

        return convertToDto(order);
    }

}
