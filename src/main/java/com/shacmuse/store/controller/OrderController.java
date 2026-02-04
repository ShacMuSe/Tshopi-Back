package com.shacmuse.store.controller;

import com.shacmuse.store.CheckoutRequest;
import com.shacmuse.store.OrderRequest;
import com.shacmuse.store.entity.Cart;
import com.shacmuse.store.entity.CartItem;
import com.shacmuse.store.entity.Order;
import com.shacmuse.store.entity.Product;
import com.shacmuse.store.repository.CartRepository;
import com.shacmuse.store.repository.OrderRepository;
import com.shacmuse.store.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public OrderController(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CartRepository cartRepository
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    // Single-product order (optional / legacy)
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

    // ✅ CART → MULTIPLE ORDERS
    @PostMapping("/checkout/{phone}")
    public void checkout(
            @PathVariable String phone,
            @RequestBody CheckoutRequest request
    ) {
        Cart cart = cartRepository.findById(phone)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        for (CartItem item : cart.getItems()) {
            Order order = new Order();
            order.setFirstName(request.getFirstName());
            order.setLastName(request.getLastName());
            order.setPhone(phone);
            order.setEmail(request.getEmail());
            order.setAddress(request.getAddress());
            order.setProduct(item.getProduct());
            order.setQuantity(item.getQuantity());
            order.setSelectedImage(
                    item.getProduct().getImageUrls() != null
                            ? item.getProduct().getImageUrls().get(0)
                            : null
            );

            orderRepository.save(order);
        }

        // clear cart after checkout
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
