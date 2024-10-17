package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * User.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 23-Jul-24
 */
@Getter
@Entity
@Table(name = "users")
public class User {


    @Column(name = "avatar")
    private String avatar;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany
    @JoinColumn(name = "user_id")
    @JsonBackReference("userReviewReference")
    @JsonIgnore
    private List<Review> review = new ArrayList<>();

    public User() {
    }

    public User(Builder builder) {

        this.avatar = builder.avatar;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.address = builder.address;
        this.review = builder.review != null ? builder.review : new ArrayList<>();
        this.roles.addAll(builder.roles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(avatar, user.avatar) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(createdAt, user.createdAt) &&
                Objects.equals(updatedAt, user.updatedAt) &&
                Objects.equals(address, user.address) &&
                Objects.equals(review, user.review) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avatar, username, password, email, firstName, lastName, createdAt, updatedAt, address, review, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                ", AVATAR: '" + avatar + '\'' +
                ", USERNAME: '" + username + '\'' +
                ", PASSWORD: '" + password + '\'' +
                ", EMAIL: '" + email + '\'' +
                ", FIRST NAME: '" + firstName + '\'' +
                ", LAST NAME: '" + lastName + '\'' +
                ", CREATED AT: " + createdAt +
                ", UPDATED AT: " + updatedAt +
                ", role=" + roles +
                ", ADDRESS: " + address +
                ", REVIEW: " + review +
                '}';
    }

    public static class Builder {

        private String avatar; // Added avatar field
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private LocalDate createdAt;
        private LocalDate updatedAt;
        private Set<String> roles = new HashSet<>();
        private Address address;
        private List<Review> review;


        public Builder setAvatar(String avatar) { // Added setAvatar method
            this.avatar = avatar;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDate updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder setRoles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setReview(List<Review> review) {
            this.review = review;
            return this;
        }

        public Builder copy(User user) {

            this.avatar = user.getAvatar(); // Added avatar copy
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.email = user.getEmail();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.createdAt = user.getCreatedAt();
            this.updatedAt = user.getUpdatedAt();
            this.address = user.getAddress();
            this.roles = new HashSet<>(user.getRoles());
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
