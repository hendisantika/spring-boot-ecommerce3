package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.entity.Category;
import id.my.hendisantika.ecommerce3.entity.Product;
import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import id.my.hendisantika.ecommerce3.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

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

    @PostMapping("/add")
    public String addProduct(
            @ModelAttribute Product product,
            @RequestParam("categoryId") int categoryId,
            @RequestParam("productImage") MultipartFile file,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        try {
            // Set category
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category == null) {
                redirectAttributes.addFlashAttribute("message", "Category not found");
                return "redirect:/product/add";
            }
            product.setCategory(category);

            // Save product
            Product savedProduct = productRepository.save(product);

            // Handle file upload if provided
            if (!file.isEmpty()) {
                String path = session.getServletContext().getRealPath("/") + "img" + File.separator + "products" + File.separator + savedProduct.getPid() + ".jpg";

                // Create directories if they don't exist
                File outputFile = new File(path);
                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }

                // Save file
                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    InputStream is = file.getInputStream();
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                }

                // Update product with photo path
                product.setPPhoto(savedProduct.getPid() + ".jpg");
                productRepository.save(product);
            }

            redirectAttributes.addFlashAttribute("message", "Product added successfully");
            return "redirect:/admin";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Error adding product: " + e.getMessage());
            return "redirect:/product/add";
        }
    }
}
