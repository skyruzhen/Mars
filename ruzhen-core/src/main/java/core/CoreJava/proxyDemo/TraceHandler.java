package core.CoreJava.proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/9/20
 * @since 1.0.0
 */
public class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object t){
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("."+ method.getName()+"(");
        if(args != null){
            for(int i = 0; i < args.length; i ++){
                System.out.print(args[i]);
                if(i < args.length - 1) System.out.print(",");
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}