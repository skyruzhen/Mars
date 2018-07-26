package com.ruzhen.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈Spring 配置〉
 *
 * @author lizhens
 * @create 2018/7/13
 * @since 1.0.0
 */

@EnableWebMvc

@EnableSwagger2


public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Bean

    public Docket customDocket() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()

                .paths(paths())

                .build()

                .pathMapping("/");

    }

    private Predicate<String> paths() {

        return PathSelectors.any();

    }

    private ApiInfo apiInfo() {

        Contact contact = new Contact("货代钱包", "https://www.huodaipay.com", "400-168-5620");

        return new ApiInfoBuilder().title("API接口").description("API接口").contact(contact).version("1.0.0").build();

    }

    @Override

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        ObjectMapper objectMapper = null;

        for (HttpMessageConverter converter : converters) {

            if (converter instanceof MappingJackson2HttpMessageConverter) {

                MappingJackson2HttpMessageConverter jacksonConverter =

                        ((MappingJackson2HttpMessageConverter) converter);

                if (objectMapper == null) {

                    objectMapper = jacksonConverter.getObjectMapper();

                } else {

                    jacksonConverter.setObjectMapper(objectMapper);

                }

            }

        }

    }

}