package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import id.my.hendisantika.ecommerce3.repository.ProductRepository;
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
 * Time: 06.01
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @GetMapping("/add")
    public String addProductForm(Model model, HttpSession session) {
        if (session.getAttribute("current-user") == null) {
            return "redirect:/login";
        }

        model.addAttribute("categories", categoryRepository.findAll());
        return "add_product";
    }
}
