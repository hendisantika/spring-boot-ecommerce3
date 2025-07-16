package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.entity.User;
import id.my.hendisantika.ecommerce3.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.55
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            // Check if user already exists
            if (userService.existsByEmail(user.getUserEmail())) {
                session.setAttribute("message", "*Email already registered*");
                return "redirect:/register";
            }

            if (userService.existsByUserName(user.getUserName())) {
                session.setAttribute("message", "*Username already taken*");
                return "redirect:/register";
            }

            // Set default values
            user.setUserPic("default.jpg");
            user.setUserType("normal");

            // Register user (this will hash the password)
            User registeredUser = userService.registerUser(user);

            session.setAttribute("message", "*Registration Successful! User id is: " + registeredUser.getUserId());
            return "redirect:/register";

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "*Registration Failed: " + e.getMessage() + "*");
            return "redirect:/register";
        }
    }
}
