package core.aop;

/**
 * 〈一句话功能简述〉<br>
 * 〈日志切面〉
 *
 * @author lizhen
 * @create 2018/7/13
 * @since 1.0.0
 */
public class Logging {
    public void beforeAdvice(){
        System.out.println("Goint to setup student profile.");
    }

    public void afterAdvice(){
        System.out.println("Student profile has been setup");
    }

    public void afterReturningAdvice(Object retVal){
        System.out.println("Returning:"+retVal.toString());
    }

    public void AfterThrowingAdvice(IllegalArgumentException ex){
        System.out.println("There has been an exception:"+ex.toString());
    }


}