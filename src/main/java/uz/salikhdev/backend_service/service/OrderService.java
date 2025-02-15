package uz.salikhdev.backend_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateOrderDto;
import uz.salikhdev.backend_service.entity.Order;
import uz.salikhdev.backend_service.entity.Product;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.repository.OrderRepository;
import uz.salikhdev.backend_service.repository.ProductRepository;
import uz.salikhdev.backend_service.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(CreateOrderDto createOrderDto) {
        Set<Product> products = (Set<Product>) productRepository.findAllById(createOrderDto.productIds());
        Customer customer = customerRepository.findById(createOrderDto.customerId()).orElseThrow();

        BigDecimal totalAmount = products.stream()
                                         .map(Product::getPrice)
                                         .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                           .customer(customer)
                           .products(products)
                           .totalAmount(totalAmount)
                           .build();

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public Order updateOrder(Long id, CreateOrderDto createOrderDto) {
        Order order = orderRepository.findById(id).orElseThrow();
        Set<Product> products = (Set<Product>) productRepository.findAllById(createOrderDto.productIds());
        BigDecimal totalAmount = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setProducts(products);
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
