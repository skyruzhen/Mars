package core.beans;

/**
 * 〈一句话功能简述〉<br>
 * 〈Spring Hello World实例〉
 *
 * @author lizhen
 * @create 2018/7/12
 * @since 1.0.0
 */
public class HelloWorld {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void init(){
        System.out.println("Bean is going through init.");
    }

    public void destroy(){
        System.out.println("Bean will destroy now.");
    }

}