package id.my.hendisantika.ecommerce3.util;

import id.my.hendisantika.ecommerce3.entity.Category;
import id.my.hendisantika.ecommerce3.entity.Product;
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

    /**
     * Seed 10 dummy categories
     */
    private void seedDummyCategories() {
        String[] categoryTitles = {
                "Electronics", "Clothing", "Books", "Home & Kitchen", "Sports",
                "Toys", "Beauty", "Automotive", "Health", "Grocery"
        };

        String[] categoryDescriptions = {
                "Electronic devices and accessories",
                "Clothing and fashion accessories",
                "Books, e-books, and publications",
                "Home appliances and kitchen essentials",
                "Sports equipment and accessories",
                "Toys and games for all ages",
                "Beauty and personal care products",
                "Automotive parts and accessories",
                "Health and wellness products",
                "Grocery and gourmet food"
        };

        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setCategoryTitle(categoryTitles[i]);
            category.setCategoryDescriptioin(categoryDescriptions[i]);
            categoryRepository.save(category);
            log.info("Created dummy category: {}", categoryTitles[i]);
        }
    }

    /**
     * Seed 10 dummy users
     */
    private void seedDummyUsers() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserName("User" + i);
            user.setUserEmail("user" + i + "@example.com");
            user.setUserPassword(passwordEncoder.encode("password" + i));
            user.setUserPhone("123456789" + i);
            user.setUserPic("default.jpg");
            user.setUserAddress("Address " + i + ", City, Country");
            // Make the first user admin, rest normal
            user.setUserType(i == 1 ? "admin" : "normal");
            userRepository.save(user);
            log.info("Created dummy user: {}", user.getUserName());
        }
    }

    /**
     * Seed 10 dummy products
     */
    private void seedDummyProducts() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            log.error("No categories found. Cannot create products.");
            return;
        }

        String[] productNames = {
                "Smartphone", "T-shirt", "Novel", "Blender", "Basketball",
                "Board Game", "Shampoo", "Car Charger", "Vitamins", "Cereal"
        };

        String[] productDescriptions = {
                "Latest smartphone with advanced features",
                "Comfortable cotton t-shirt",
                "Bestselling novel by a renowned author",
                "High-powered blender for smoothies",
                "Professional basketball for indoor/outdoor use",
                "Fun board game for the whole family",
                "Nourishing shampoo for all hair types",
                "Fast charging car charger for devices",
                "Daily vitamins for overall health",
                "Healthy breakfast cereal"
        };

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setpName(productNames[i]);
            product.setpDesc(productDescriptions[i]);
            product.setpPhoto("default.jpg");
            product.setpPrice(1000 + random.nextInt(9000)); // Random price between 1000 and 10000
            product.setpDiscount(random.nextInt(30)); // Random discount between 0 and 29%
            product.setpQuantity(10 + random.nextInt(90)); // Random quantity between 10 and 99

            // Assign a category - match product to appropriate category or random if not enough
            int categoryIndex = Math.min(i, categories.size() - 1);
            product.setCategory(categories.get(categoryIndex));

            productRepository.save(product);
            log.info("Created dummy product: {}", product.getpName());
        }
    }
}
