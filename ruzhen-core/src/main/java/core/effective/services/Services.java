package core.effective.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈Moninstantiable class for service registrantion and access〉
 *
 * @author lizhen
 * @create 2018/6/15
 * @since 1.0.0
 */
public class Services {
    private Services(){}

    //Maps service names to services
    private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();
    public static final String DEFAULT_PROVIDER_NAME = "<def>";
    //Provider registration API
    public static void registerDefaultProvider(Provider p){
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }
    public static void registerProvider(String name, Provider p){
        providers.put(name, p);
    }

    //Service access API
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    public static Service newInstance(String name){
        //第1条 考虑用静态工厂方法代替构造器
        Provider p = providers.get(name);
        if(p == null){
            throw new IllegalArgumentException("No provider registered with name:"+name);
        }
        return p.newService();
    }


}