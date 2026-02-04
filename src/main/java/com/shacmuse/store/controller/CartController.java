package com.shacmuse.store.controller;

import com.shacmuse.store.AddToCartRequest;
import com.shacmuse.store.CartService;
import com.shacmuse.store.entity.Cart;
import com.shacmuse.store.repository.CartRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartRepository cartRepo;
    private final CartService cartService;

    public CartController(CartRepository cartRepo, CartService cartService) {
        this.cartRepo = cartRepo;
        this.cartService = cartService;
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

}

