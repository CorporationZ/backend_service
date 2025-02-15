package uz.salikhdev.backend_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.salikhdev.backend_service.entity.Customer;
import uz.salikhdev.backend_service.entity.Product;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders") // "order" SQL da maxsus so‘z bo‘lgani uchun "orders" deb nomlaymiz
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
