package com.shacmuse.store.controller;

import com.shacmuse.store.entity.Order;
import com.shacmuse.store.OrderRequest;
import com.shacmuse.store.entity.Product;
import com.shacmuse.store.repository.OrderRepository;
import com.shacmuse.store.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow();

        Order order = new Order();
        order.setFirstName(request.getFirstName());
        order.setLastName(request.getLastName());
        order.setPhone(request.getPhone());
        order.setEmail(request.getEmail());
        order.setAddress(request.getAddress());
        order.setQuantity(request.getQuantity());
        order.setProduct(product);
        order.setSelectedImage(request.getSelectedImage());


        return orderRepository.save(order);
    }
}
