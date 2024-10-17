package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;

import java.util.List;

/**
 * IUserService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 24-Jul-24
 */

@Service
public interface IUserService extends IService<User, Long> {

    User createUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

}
