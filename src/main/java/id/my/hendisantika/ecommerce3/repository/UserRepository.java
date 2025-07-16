package id.my.hendisantika.ecommerce3.repository;

import id.my.hendisantika.ecommerce3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.46
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find a user by their email address
     *
     * @param email the email address to search for
     * @return an Optional containing the user if found
     */
    Optional<User> findByUserEmail(String email);

    /**
     * Check if a user exists with the given email
     *
     * @param email the email address to check
     * @return true if a user exists with the email
     */
    boolean existsByUserEmail(String email);

    /**
     * Check if a user exists with the given username
     *
     * @param userName the username to check
     * @return true if a user exists with the username
     */
    boolean existsByUserName(String userName);

    /**
     * Find a user by their email and password
     *
     * @param userEmail    the email address to search for
     * @param userPassword the password to match
     * @return the user if found, null otherwise
     */
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);
}
