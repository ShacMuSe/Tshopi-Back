package com.shacmuse.store.entity;

public enum OrderStatus {
    PENDING,        // order created
    CONFIRMED,      // confirmed by admin
    SHIPPED,        // sent to customer
    DELIVERED,      // received
    CANCELLED
}
