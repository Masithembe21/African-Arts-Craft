package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * UserRepository.java
 *
 * Repository interface for User entity.
 *
 * Provides CRUD operations and custom query methods.
 *
 * Author: Rethabile Ntsekhe
 * Student Num: 220455430
 * Date: 24-Jul-24
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
