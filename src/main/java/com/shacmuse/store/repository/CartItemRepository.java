package com.shacmuse.store.repository;

import com.shacmuse.store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartPhoneAndProductId(String phone, Long productId);
}
