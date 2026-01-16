package com.shacmuse.store.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private Double price;

    // ✅ Images
    @ElementCollection
    @CollectionTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "image_url")
    private List<String> imageUrls;

    // ✅ Videos
    @ElementCollection
    @CollectionTable(
            name = "product_videos",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "video_url")
    private List<String> videoUrls;

    public Product() {}

    public Product(
            Long id,
            String name,
            String category,
            Double price,
            List<String> imageUrls,
            List<String> videoUrls
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrls = imageUrls;
        this.videoUrls = videoUrls;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }

    public List<String> getVideoUrls() { return videoUrls; }
    public void setVideoUrls(List<String> videoUrls) { this.videoUrls = videoUrls; }
}
