package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.CartItem;
import za.ac.cput.domain.Product;

/**
 * Factory class for creating instances of {@link CartItem}.
 * Provides static methods to create {@link CartItem} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 24-Aug-24
 */
public class CartItemFactory {

    /**
     * Creates a {@link CartItem} instance from various inputs.
     *
     * @param id       the unique identifier for the cart item
     * @param cart     the {@link Cart} entity associated with this cart item
     * @param product  the {@link Product} entity associated with this cart item
     * @param quantity the quantity of the cart item
     * @return a new {@link CartItem} object with properties set from the input parameters
     * @throws IllegalArgumentException if any of the input parameters are invalid
     */
    public static CartItem createCartItem(Long id,
                                          Cart cart,
                                          Product product,
                                          int quantity) {
        // Validate inputs
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        // Use the Builder pattern to create a new CartItem object
        return new CartItem.Builder()
                .setId(id)
                .setCart(cart)        // Set the cart associated with the cart item
                .setProduct(product)  // Set the product associated with the cart item
                .setQuantity(quantity) // Set the quantity of the cart item
                .build();
    }
}
