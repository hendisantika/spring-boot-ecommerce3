package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.entity.User;
import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import id.my.hendisantika.ecommerce3.repository.ProductRepository;
import id.my.hendisantika.ecommerce3.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.54
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @GetMapping
    public String adminDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("current-user");

        // Check if user is admin
        if (user == null || !user.getUserType().equals("admin")) {
            return "redirect:/login";
        }

        // Count of users, categories, and products
        long userCount = userRepository.count();
        long categoryCount = categoryRepository.count();
        long productCount = productRepository.count();

        model.addAttribute("userCount", userCount);
        model.addAttribute("categoryCount", categoryCount);
        model.addAttribute("productCount", productCount);
        model.addAttribute("categories", categoryRepository.findAll());

        return "admin";
    }
}
