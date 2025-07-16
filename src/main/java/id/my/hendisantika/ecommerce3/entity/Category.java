package id.my.hendisantika.ecommerce3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.39
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryTitle;
    private String categoryDescriptioin;
    @OneToMany(mappedBy = "category")
    private final List<Product> products = new ArrayList<Product>();

    public Category(int categoryId, String categoryTitle, String categoryDescriptioin) {
        super();
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDescriptioin = categoryDescriptioin;
    }
}
