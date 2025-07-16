package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.entity.Category;
import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.58
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public String checkoutPage(Model model, HttpSession session) {
        // Get categories for navbar
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "checkout";
    }

    @PostMapping("/order/place")
    public String placeOrder(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            // In a real application, you would save the order to the database here

            // Add success message
            redirectAttributes.addFlashAttribute("message", "Order placed successfully! Thank you for shopping with us.");

            return "redirect:/";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Error placing order: " + e.getMessage());
            return "redirect:/checkout";
        }
    }
}
