package za.ac.cput.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity representing an Address.
 * Each address is linked to a user and stores information like city, country, and postal code.
 */
@Entity
@Getter
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String postalCode;
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public Address() {
    }

    private Address(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.city = builder.city;
        this.country = builder.country;
        this.postalCode = builder.postalCode;
        this.phoneNumber = builder.phoneNumber;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "\n Address{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(title, address.title) &&
                Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(addressLine2, address.addressLine2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(phoneNumber, address.phoneNumber) &&
                Objects.equals(createdAt, address.createdAt) &&
                Objects.equals(updatedAt, address.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, addressLine1, addressLine2, city, country, postalCode, phoneNumber, createdAt, updatedAt);
    }

    // Builder class for Address
    public static class Builder {
        private Long id;
        private String title;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String country;
        private String postalCode;
        private String phoneNumber;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder copy(Address address) {
            this.id = address.getId();
            this.title = address.getTitle();
            this.addressLine1 = address.getAddressLine1();
            this.addressLine2 = address.getAddressLine2();
            this.city = address.getCity();
            this.country = address.getCountry();
            this.postalCode = address.getPostalCode();
            this.phoneNumber = address.getPhoneNumber();
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
