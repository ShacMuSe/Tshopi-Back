package com.shacmuse.store;

import com.shacmuse.store.entity.Cart;
import com.shacmuse.store.entity.CartItem;
import com.shacmuse.store.repository.CartItemRepository;
import com.shacmuse.store.repository.CartRepository;
import com.shacmuse.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository itemRepo;
    private final ProductRepository productRepo;

    public CartService(
            CartRepository cartRepo,
            CartItemRepository itemRepo,
            ProductRepository productRepo
    ) {
        this.cartRepo = cartRepo;
        this.itemRepo = itemRepo;
        this.productRepo = productRepo;
    }

    public Cart addToCart(AddToCartRequest req) {
        Cart cart = cartRepo.findById(req.getPhone())
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setPhone(req.getPhone());
                    return cartRepo.save(c);
                });

        CartItem item = itemRepo
                .findByCartPhoneAndProductId(req.getPhone(), req.getProductId())
                .orElseGet(() -> {
                    CartItem ci = new CartItem();
                    ci.setCart(cart);
                    ci.setProduct(
                            productRepo.findById(req.getProductId()).orElseThrow()
                    );
                    ci.setQuantity(0);
                    return ci;
                });

        item.setQuantity(item.getQuantity() + req.getQuantity());
        itemRepo.save(item);

        return cart;
    }

    public void removeItem(Long itemId) {
        CartItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        Cart cart = item.getCart();
        cart.removeItem(item); // keeps relationship clean

        itemRepo.delete(item);
    }

}
