package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateProductDto;
import uz.salikhdev.backend_service.entity.Product;
import uz.salikhdev.backend_service.exception.EntityAlreadyExistsException;
import uz.salikhdev.backend_service.exception.EntityNotFound;
import uz.salikhdev.backend_service.repositroy.OrderRepository;
import uz.salikhdev.backend_service.repositroy.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public void createProduct(CreateProductDto dto){
        if (productRepository.existsByName(dto.name())){
            log.error("Product with the name:" + dto.name() + "is already exists");
            throw new EntityAlreadyExistsException("Product with the name:" + dto.name() + "is already exists");
        }

        Product product = Product.builder()
                .name(dto.name())
                .price(dto.price())
                .build();
        productRepository.save(product);
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFound("Product not found id : %s".formatted(id))
        );
    }
}
