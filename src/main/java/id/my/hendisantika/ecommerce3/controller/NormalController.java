package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.entity.Category;
import id.my.hendisantika.ecommerce3.entity.User;
import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 06.00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/normal")
public class NormalController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public String normalDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("current-user");

        // Check if user is normal user
        if (user == null || !user.getUserType().equals("normal")) {
            return "redirect:/login";
        }

        // Get categories for navbar
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "normal";
    }
}
