package core.Spring.aop.javaconfig;

import org.springframework.stereotype.Service;

@Service
public class DemoMethodService {
    @Action(name="方法规则式拦截的add操作")
    public void add(){}
}
