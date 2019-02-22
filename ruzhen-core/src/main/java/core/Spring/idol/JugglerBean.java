package core.Spring.idol;

import org.springframework.context.annotation.Bean;

public class JugglerBean {
    @Bean
    public JugglerBean duke(){
        return new JugglerBean();
    }
}
