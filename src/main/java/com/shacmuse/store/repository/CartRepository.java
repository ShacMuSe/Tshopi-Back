package com.shacmuse.store.repository;

import com.shacmuse.store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {}
