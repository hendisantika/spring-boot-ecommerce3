package id.my.hendisantika.ecommerce3.dto;

import id.my.hendisantika.ecommerce3.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.44
 * To change this template use File | Settings | File Templates.
 */
public class ProductDao {
    private final SessionFactory factory;

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }


    public boolean saveProduct(Product product) {
        boolean f = false;

        try {

            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(product);
            f = true;


            tx.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            f = false;
        }
        return f;
    }


    //get all products


    public List<Product> getAllProducts() {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Product");
        List<Product> list = query.list();
        return list;

    }

    //get all products of given category
    public List<Product> getAllProductsById(int cid) {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Product as p where p.category.categoryId=:id");
        query.setParameter("id", cid);
        List<Product> list = query.list();
        return list;

    }
}
