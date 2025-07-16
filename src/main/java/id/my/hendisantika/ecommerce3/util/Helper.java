package id.my.hendisantika.ecommerce3.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ecommerce3
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 17/07/25
 * Time: 05.51
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
    public static String get10Words(String desc) {
        String[] strs = desc.split(" ");


        if (strs.length > 10) {
            String res = "";
            for (int i = 0; i < 10; i++) {
                res = res + strs[i] + " ";
            }
            return (res + "...");
        } else {
            return (desc + "...");
        }
    }

    public static Map<String, Long> getCounts(SessionFactory factory) {
        Session session = factory.openSession();
        String q1 = "Select count(*) from User";
        String q2 = "Select count(*) from Product";

        Query query1 = session.createQuery(q1);
        Query query2 = session.createQuery(q2);

        Long userCount = (Long) query1.list().get(0);
        Long productCount = (Long) query2.list().get(0);

        Map<String, Long> map = new HashMap<String, Long>();
        map.put("userCount", userCount);
        map.put("productCount", productCount);


        session.close();

        return map;
    }
}
