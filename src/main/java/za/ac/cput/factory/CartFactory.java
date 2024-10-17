package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

/**
 * Factory class for creating instances of {@link Cart}.
 * Provides static methods to create {@link Cart} objects from various inputs.
 *
 * @author Rethabile Ntsekhe
 * @date 24-Aug-24
 */
public class CartFactory {

    /**
     * Creates a {@link Cart} instance from various inputs.
     *
     * @param id        the ID of the cart
     * @param user      the {@link User} entity associated with this cart
     * @param total     the total cost of the cart
     * @param itemCount the total number of items in the cart
     * @param status    the status of the cart (e.g., active, abandoned, checked out)
     * @return a new {@link Cart} object with properties set from the input parameters
     */
    public static Cart createCart(Long id,
                                  User user,
                                  Double total,
                                  Integer itemCount,
                                  String status
    ) {

        if (Helper.isNullOrEmpty(user) ||
                Helper.isDoubleNullOrEmpty(total) ||
                Helper.isNullOrEmpty(itemCount) ||
                Helper.isNullOrEmpty(status)
        ) {
            throw new IllegalArgumentException("User, total, item count, and status cannot be null");
        }

        return new Cart.Builder()
                .setId(id)
                .setUser(user)
                .setTotal(total)
                .setItemCount(itemCount) // Set item count
                .setStatus(status) // Set cart status
                .build();
    }
}
