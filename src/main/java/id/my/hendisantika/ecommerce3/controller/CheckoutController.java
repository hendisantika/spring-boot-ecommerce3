package id.my.hendisantika.ecommerce3.controller;

import id.my.hendisantika.ecommerce3.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
