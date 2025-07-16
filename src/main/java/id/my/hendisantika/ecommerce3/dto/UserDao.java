package id.my.hendisantika.ecommerce3.dto;

import id.my.hendisantika.ecommerce3.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.45
 * To change this template use File | Settings | File Templates.
 */
public class UserDao {
    private final SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    //getuser by email and password
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {

            String query = "from User where userEmail=:e and userPassword=:p";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("e", email);
            q.setParameter("p", password);
            user = (User) q.uniqueResult();

            session.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;


    }
}
