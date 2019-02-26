package core.Spring.aop.javaconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("core.Spring.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
