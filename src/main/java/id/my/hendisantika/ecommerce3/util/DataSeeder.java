package id.my.hendisantika.ecommerce3.util;

import id.my.hendisantika.ecommerce3.entity.User;
import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import id.my.hendisantika.ecommerce3.repository.ProductRepository;
import id.my.hendisantika.ecommerce3.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.51
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder {

    // Pattern to check if a password is already BCrypt hashed
    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2[ayb]\\$.{56}\\z");
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new Random();

    @PostConstruct
    @Transactional
    public void seedData() {
        updateExistingUserPasswords();

        // Check if we need to seed dummy data
        if (categoryRepository.count() == 0) {
            log.info("Seeding dummy categories...");
            seedDummyCategories();
        }

        if (userRepository.count() < 10) {
            log.info("Seeding dummy users...");
            seedDummyUsers();
        }

        if (productRepository.count() == 0) {
            log.info("Seeding dummy products...");
            seedDummyProducts();
        }
    }

    /**
     * Update existing user passwords to use BCrypt if they're not already hashed
     */
    private void updateExistingUserPasswords() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            // Check if the password is already BCrypt hashed
            if (!BCRYPT_PATTERN.matcher(user.getUserPassword()).matches()) {
                // Hash the password and update the user
                String hashedPassword = passwordEncoder.encode(user.getUserPassword());
                user.setUserPassword(hashedPassword);
                userRepository.save(user);
                log.info("Updated password for user: {}", user.getUserName());
            }
        }
    }
}
