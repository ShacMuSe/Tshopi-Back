package com.shacmuse.store.repository;

import com.shacmuse.store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // old method — only product + phone
    // Optional<CartItem> findByCartPhoneAndProductId(String phone, Long productId);

    // new method — include selectedImage
    Optional<CartItem> findByCartPhoneAndProductIdAndSelectedImage(
            String phone,
            Long productId,
            String selectedImage
    );
}