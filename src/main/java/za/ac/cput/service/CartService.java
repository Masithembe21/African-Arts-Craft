package za.ac.cput.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Cart;
import za.ac.cput.factory.CartFactory;
import za.ac.cput.repository.CartRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CartService.java
 * <p>
 * Service implementation for managing Cart entities.
 * Implements ICartService to provide basic CRUD operations and additional query methods.
 *
 * @autor Rethabile Ntsekhe
 * @date 25-Aug-24
 */

@Slf4j
@Service
@Transactional
public class CartService implements ICart {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
    }

    /**
     * Creates a new Cart.
     *
     * @param cart the Cart entity to be created
     * @return the created Cart
     */
    @Override
    public Cart create(Cart cart) {
        // Ensure itemCount and status are set appropriately before saving

        // Create the Cart using the builder pattern
        Cart newCart = new Cart.Builder()
                .setUser(cart.getUser())
                .setTotal(cart.getTotal())
                .setItemCount(cart.getItemCount())
                .setStatus(cart.getStatus())
                .build();

        return cartRepository.save(newCart);
    }


    /**
     * Reads a Cart by its ID.
     *
     * @param id the ID of the Cart to be read
     * @return the Cart entity if found, or null if not found
     */
    @Override
    public Cart read(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing Cart.
     *
     * @param cartDetails the Cart entity to be updated
     * @return the updated Cart entity, or null if the Cart does not exist
     */
    @Override
    public Cart update(Cart cartDetails) {
        if (cartDetails.getId() == null || !cartRepository.existsById(cartDetails.getId())) {
            throw new IllegalArgumentException("Cart with the given ID does not exist.");
        }

        // Retrieve the existing cart
        Cart existingCart = cartRepository.findById(cartDetails.getId()).orElseThrow();

        // Create an updated cart using the builder pattern
        Cart updatedCart = new Cart.Builder()
                .copy(existingCart) // Copy the existing cart's details
                .setTotal(cartDetails.getTotal())
                .setItemCount(cartDetails.getItemCount())
                .setStatus(cartDetails.getStatus())
                .build();

        return cartRepository.save(updatedCart);
    }

    @Override
    public List<Cart> findAll() {
        return List.of();
    }


    /**
     * Deletes a Cart and cart items by its Cart ID.
     *
     * @param id the ID of the Cart to be deleted
     * @return true if deleted successfully, otherwise false
     */
    public boolean delete(Long id) {
        cartItemService.deleteByCartId(id);
        cartRepository.deleteById(id); // Use deleteById (standard JpaRepository method)

        // Check if the entity still exists after deletion
        boolean exists = cartRepository.existsById(id);

        // Return true if it no longer exists (successful deletion), otherwise return false
        return !exists;
    }

    // ... (rest of the methods remain unchanged)
}
