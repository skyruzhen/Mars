package core.CoreJava;

import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈反射机制〉
 *
 * @author lizhen
 * @create 2018/9/19
 * @since 1.0.0
 */
public class ReflectionDemo {
    public static void main(String[] args) {
        String name = "";
        try {
            Class c = Class.forName(name);
            Method[] methods = c.getMethods();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}