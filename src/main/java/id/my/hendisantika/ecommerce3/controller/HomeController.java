package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.59
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        // Add authenticated user to model if available
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof User user) {
            model.addAttribute("user", user);
        }

        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        // Add authenticated user to model if available
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof User user) {
            model.addAttribute("user", user);
        }

        return "about";
    }
}
