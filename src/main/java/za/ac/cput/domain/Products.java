package za.ac.cput.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Products.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 23-Jul-24
 */

@Entity
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private long categoryId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String imagePath;

    public Products() {
    }

    public Products(Builder builder) {
        this.productId = builder.productId;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.stockQuantity = builder.stockQuantity;
        this.categoryId = builder.categoryId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.imagePath = builder.imagePath;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products products)) return false;

        if (getProductId() != products.getProductId()) return false;
        if (Double.compare(getPrice(), products.getPrice()) != 0) return false;
        if (getStockQuantity() != products.getStockQuantity()) return false;
        if (getCategoryId() != products.getCategoryId()) return false;
        if (getName() != null ? !getName().equals(products.getName()) : products.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(products.getDescription()) : products.getDescription() != null)
            return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(products.getCreatedAt()) : products.getCreatedAt() != null)
            return false;
        if (getUpdatedAt() != null ? !getUpdatedAt().equals(products.getUpdatedAt()) : products.getUpdatedAt() != null)
            return false;
        return getImagePath() != null ? getImagePath().equals(products.getImagePath()) : products.getImagePath() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getProductId() ^ (getProductId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getStockQuantity();
        result = 31 * result + (int) (getCategoryId() ^ (getCategoryId() >>> 32));
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        result = 31 * result + (getImagePath() != null ? getImagePath().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Products{" +
                "Product ID: " + productId +
                ", NAME: '" + name + '\'' +
                ", DESCRIPTION: '" + description + '\'' +
                ", PRICE: " + price +
                ", STOCK QUANTITY: " + stockQuantity +
                ", CATEGORY ID: " + categoryId +
                ", CREATED AT: " + createdAt +
                ", UPDATED AT: " + updatedAt +
                ", IMAGE PATH: '" + imagePath + '\'' +
                '}';
    }

    public static class Builder {
        private long productId;
        private String name;
        private String description;
        private double price;
        private int stockQuantity;
        private long categoryId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String imagePath;

        public Builder setProductId(long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder setCategoryId(long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder copy(Products products) {
            this.productId = products.getProductId();
            this.name = products.getName();
            this.description = products.getDescription();
            this.price = products.getPrice();
            this.stockQuantity = products.getStockQuantity();
            this.categoryId = products.getCategoryId();
            this.createdAt = products.getCreatedAt();
            this.updatedAt = products.getUpdatedAt();
            this.imagePath = products.getImagePath();
            return this;
        }

        public Products build() {
            return new Products(this);
        }
    }
}
