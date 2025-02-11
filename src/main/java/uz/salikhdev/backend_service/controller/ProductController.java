package uz.salikhdev.backend_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.salikhdev.backend_service.dto.MessageDto;
import uz.salikhdev.backend_service.dto.ProductCreateDto;
import uz.salikhdev.backend_service.service.ProductService;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<MessageDto> createProduct(@RequestBody ProductCreateDto dto) {
        productService.saveProduct(dto);
        return ResponseEntity.ok(new MessageDto("Product created", true));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
