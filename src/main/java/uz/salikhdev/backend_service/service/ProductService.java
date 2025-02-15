package uz.salikhdev.backend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.salikhdev.backend_service.entity.Product;
import uz.salikhdev.backend_service.exceotion.EntityAlreadyExistsException;
import uz.salikhdev.backend_service.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(Product product) {
        if (productRepository.existsByProductName(product.getProductName())) {
            log.error("Product with name {} already exists", product.getProductName());
            throw new EntityAlreadyExistsException("Product with name " + product.getProductName() + " already exists");
        }
        productRepository.save(product);
    }


    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, Product product) {
        Product existingProduct = getProduct(id);

        Product updatedProduct = Product.builder()
                .id(existingProduct.getId())
                .productName(product.getProductName() != null ? product.getProductName() : existingProduct.getProductName())
                .price(product.getPrice() != null ? product.getPrice() : existingProduct.getPrice())
                .build();

        productRepository.save(updatedProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
