package com.shacmuse.store;

public class UpdateCartItemRequest {

    private int quantity;
    private String selectedImage; // optional

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getSelectedImage() { return selectedImage; }
    public void setSelectedImage(String selectedImage) { this.selectedImage = selectedImage; }
}