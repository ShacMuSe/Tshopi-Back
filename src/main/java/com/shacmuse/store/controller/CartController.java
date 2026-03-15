package com.shacmuse.store.controller;

import com.shacmuse.store.AddToCartRequest;
import com.shacmuse.store.CartService;
import com.shacmuse.store.UpdateCartItemRequest;
import com.shacmuse.store.entity.Cart;
import com.shacmuse.store.entity.CartItem;
import com.shacmuse.store.repository.CartItemRepository;
import com.shacmuse.store.repository.CartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartRepository cartRepo;
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;

    public CartController(
            CartRepository cartRepo,
            CartService cartService,
            CartItemRepository cartItemRepository
    ) {
        this.cartRepo = cartRepo;
        this.cartService = cartService;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/{phone}")
    public Cart getCart(@PathVariable String phone) {
        return cartRepo.findById(phone)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setPhone(phone);
                    return cartRepo.save(cart);
                });
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request);
    }

    @DeleteMapping("/item/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
    }

    @PutMapping("/items/{id}")
    public CartItem updateCartItem(
            @PathVariable Long id,
            @RequestBody UpdateCartItemRequest request
    ) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // delete if quantity <= 0
        if (request.getQuantity() <= 0) {
            cartItemRepository.delete(item);
            return null;
        }

        // just update quantity and selectedImage
        item.setQuantity(request.getQuantity());
        item.setSelectedImage(request.getSelectedImage() != null ? request.getSelectedImage() : "");

        return cartItemRepository.save(item);
    }

}

