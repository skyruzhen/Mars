package core.Spring.mvc4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext);
        ctx.setAllowBeanDefinitionOverriding(false);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispathcer", new DispatcherServlet(ctx));
        servlet.addMapping("*.jsp");
        servlet.setLoadOnStartup(2);
    }
}
