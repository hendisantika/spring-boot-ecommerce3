package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.entity.Category;
import id.my.hendisantika.ecommerce3.entity.User;
import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.57
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @PostMapping("/add")
    public String addCategory(
            @ModelAttribute Category category,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("current-user");

        // Check if user is admin
        if (user == null || !user.getUserType().equals("admin")) {
            redirectAttributes.addFlashAttribute("message", "You are not authorized to perform this action");
            return "redirect:/login";
        }

        try {
            // Save category
            categoryRepository.save(category);
            redirectAttributes.addFlashAttribute("message", "Category added successfully");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Error adding category: " + e.getMessage());
        }

        return "redirect:/admin";
    }
}
