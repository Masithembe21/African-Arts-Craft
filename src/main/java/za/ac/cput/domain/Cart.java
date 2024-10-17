package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents a cart entry in the system.
 * Each entry is associated with a User.
 *
 * This entity class is mapped to the "cart" table in the database.
 * Includes necessary mappings for relationships to other entities.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */
@Entity
@Getter
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double total;

    private Integer itemCount; // New field for total item count

    private String status; // New field for cart status (e.g., active, abandoned, checked out)

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cartReference")
    private List<CartItem> cartItems;

    public Cart() {
        this.itemCount = 0; // Initialize item count
        this.status = "active"; // Default status
    }

    private Cart(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.total = builder.total;
        this.itemCount = builder.itemCount;
        this.status = builder.status;
    }

    @Override
    public String toString() {
        return "\n Cart{" +
                "id=" + id +
                ", user=" + user.getFirstName() +
                ", total=" + total +
                ", itemCount=" + itemCount + // Include item count in toString
                ", status='" + status + '\'' + // Include status in toString
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(user, cart.user) &&
                Objects.equals(total, cart.total) &&
                Objects.equals(itemCount, cart.itemCount) && // Compare item count
                Objects.equals(status, cart.status) && // Compare status
                Objects.equals(createdAt, cart.createdAt) &&
                Objects.equals(updatedAt, cart.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, total, itemCount, status, createdAt, updatedAt); // Include new fields
    }

    public static class Builder {
        private Long id;
        private User user;
        private Double total;
        private Integer itemCount; // New field for total item count
        private String status; // New field for cart status

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setTotal(Double total) {
            this.total = total;
            return this;
        }

        public Builder setItemCount(Integer itemCount) {
            this.itemCount = itemCount;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder copy(Cart cart) {
            this.id = cart.getId();
            this.user = cart.getUser();
            this.total = cart.getTotal();
            this.itemCount = cart.getItemCount(); // Copy item count
            this.status = cart.getStatus(); // Copy status
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}
