package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.dto.ProductCreateDto;
import uz.salikhdev.backend_service.entitiy.Category;
import uz.salikhdev.backend_service.entitiy.Product;
import uz.salikhdev.backend_service.repositroy.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(ProductCreateDto dto) {

        Category category = categoryService.getCategoryById(dto.categoryId());

        Product product = Product.builder()
                .name(dto.name())
                .price(BigDecimal.valueOf(dto.price()))
                .category(category)
                .build();

        productRepository.save(product);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

}
