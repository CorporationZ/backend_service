package uz.salikhdev.backend_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.request.CreateProductDto;
import uz.salikhdev.backend_service.entity.Product;
import uz.salikhdev.backend_service.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(CreateProductDto createProductDto) {
        Product product = Product.builder()
                .name(createProductDto.name())
                .price(createProductDto.price())
                .build();

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product updateProduct(Long id, CreateProductDto createProductDto) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(createProductDto.name());
        product.setPrice(createProductDto.price());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
