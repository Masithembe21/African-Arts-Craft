package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;
import za.ac.cput.domain.*;

import java.time.LocalDate;

/**
 * Userfactory.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 24-Jul-24
 */

public class Userfactory {

    public static User buildUser(Long id,
                                 Address address,
                                 String username,
                                 String password,
                                 String email,
                                 String first_name,
                                 String last_name,
                                 LocalDate created_at,
                                 LocalDate updated_at) {

        if (Helper.isNullOrEmpty(username) ||
                Helper.isNullOrEmpty(password) ||
                Helper.isNullOrEmpty(first_name) ||
                Helper.isNullOrEmpty(last_name)
        ) return null;

        String regex = "^(.+)@(\\S+)$";
        if (!Helper.isEmailValid(email, regex) ||
                Helper.isNullOrEmpty(email)
        ) return null;

        return new User.Builder()
                .setAddress(address)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setFirstName(first_name)
                .setLastName(last_name)
                .setCreatedAt(created_at)
                .setUpdatedAt(updated_at)
                .build();
    }
}
