package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.salikhdev.backend_service.dto.request.CreateCustomerDto;
import uz.salikhdev.backend_service.dto.request.CreateOrderDto;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.entity.Order;
import uz.salikhdev.backend_service.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto dto) {
//        orderService.createOrder(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<?> getOrders() {
        Object orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
