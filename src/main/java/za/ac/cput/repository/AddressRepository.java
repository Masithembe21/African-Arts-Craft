package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * AddressRepository.java
 *
 * Repository interface for accessing Address entities from the database.
 * Extends JpaRepository to provide basic CRUD operations.
 * Custom query methods are defined to find Addresses by specific fields.
 *
 * @author Rethabile Ntsekhe
 * @date 25-Aug-24
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
