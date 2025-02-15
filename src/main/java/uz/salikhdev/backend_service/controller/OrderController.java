package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.backend_service.entity.Order;
import uz.salikhdev.backend_service.service.OrderService;

import java.util.Set;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestParam Long customerId, @RequestParam Set<Long> productIds) {
        orderService.createOrder(customerId, productIds);
        return ResponseEntity.ok("Order successfully created");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestParam Long customerId, @RequestParam Set<Long> productIds) {
        orderService.updateOrder(id, customerId, productIds);
        return ResponseEntity.ok("Order successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order successfully deleted");
    }
}