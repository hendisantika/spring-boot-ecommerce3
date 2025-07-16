package id.my.hendisantika.ecommerce3.util;

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
}
