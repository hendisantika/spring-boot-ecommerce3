package id.my.hendisantika.ecommerce3.service;

import id.my.hendisantika.ecommerce3.entity.User;
import id.my.hendisantika.ecommerce3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.48
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Register a new user with encrypted password
     *
     * @param user the user to register
     * @return the saved user
     */
    public User registerUser(User user) {
        // Encode the password before saving
        user.setUserPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Find a user by email
     *
     * @param email the email to search for
     * @return the user if found, null otherwise
     */
    public User findByEmail(String email) {
        return userRepository.findByUserEmail(email).orElse(null);
    }

    /**
     * Check if a user exists with the given email
     *
     * @param email the email to check
     * @return true if a user exists with the email
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByUserEmail(email);
    }

    /**
     * Check if a user exists with the given username
     *
     * @param userName the username to check
     * @return true if a user exists with the username
     */
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }
}
