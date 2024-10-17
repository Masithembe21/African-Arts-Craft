package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.factory.Userfactory;
import za.ac.cput.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * UserService.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 24-Jul-24
 */

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final Userfactory userfactory;

    public User validateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
