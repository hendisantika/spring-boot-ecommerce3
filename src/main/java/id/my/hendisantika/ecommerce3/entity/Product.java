package id.my.hendisantika.ecommerce3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.41
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Pid;
    private String pName;
    @Column(length = 3000)
    private String pDesc;
    private String pPhoto;
    private int price;
    private int discount;
    private int quantity;
    @ManyToOne
    private Category category;

    //calculate price after discount
    public int getPriceAfterApplyDiscount() {
        int d = (int) ((this.getDiscount() / 100.0) * this.getPrice());
        return this.getPrice() - d;
    }
}
