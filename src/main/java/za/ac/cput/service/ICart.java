package za.ac.cput.service;

import za.ac.cput.domain.Cart;

/**
 * ICartService.java
 * <p>
 * Service interface for managing Cart entities.
 * Extends IService to provide basic CRUD operations.
 *
 * @autor Rethabile Ntsekhe
 * @date 25-Aug-24
 */
public interface ICart extends IService<Cart, Long> {

    /**
     * Creates a new Cart.
     *
     * @param cart the Cart entity to be created
     * @return the created Cart
     */
    Cart create(Cart cart);

    /**
     * Reads a Cart by its ID.
     *
     * @param id the ID of the Cart to be read
     * @return the Cart entity if found, or null if not found
     */
    Cart read(Long id);

    /**
     * Updates an existing Cart.
     *
     * @param cartDetails the Cart entity to be updated
     * @return the updated Cart entity, or null if the Cart does not exist
     */
    Cart update(Cart cartDetails);

    /**
     * Deletes a Cart by its ID.
     *
     * @param id the ID of the Cart to be deleted
     * @return true if deleted successfully, otherwise false
     */
    boolean delete(Long id);
}
