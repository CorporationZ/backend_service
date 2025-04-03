package uz.salikhdev.backend_service.entity;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class Order implements Serializable {
    private String id;
    private String product;
    private int quantity;
    public Order() {}


}
