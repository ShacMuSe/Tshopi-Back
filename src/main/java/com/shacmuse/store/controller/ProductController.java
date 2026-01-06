package com.shacmuse.store.controller;

import com.shacmuse.store.entity.Product;
import com.shacmuse.store.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    // Inject the repository via constructor
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET all products
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // POST a new product
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // DELETE a product by id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    // GET a product by id (optional)
    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
