package id.my.hendisantika.ecommerce3.repository;

import id.my.hendisantika.ecommerce3.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.46
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
