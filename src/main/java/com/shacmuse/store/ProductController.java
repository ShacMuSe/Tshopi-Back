package com.shacmuse.store;


import org.springframework.web.bind.annotation.*;
import com.shacmuse.store.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = {
        "http://localhost:5173",           // dev
        "https://tshopi.vercel.app" // production
})
public class ProductController {

    private final List<Product> products = new ArrayList<>();
    private Long currentId = 1L;

    @GetMapping
    public List<Product> getAll() {
        return products;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        product.setId(currentId++);
        products.add(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
