package core.Spring.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {
    @Bean
    @Conditional(WindowsCondition.class)  //1 符合WindowsCondition条件则注入
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)  //2 符合LinuxCondition条件则注入
    public ListService linuxListService() {
        return new LinuxListService();
    }
}
