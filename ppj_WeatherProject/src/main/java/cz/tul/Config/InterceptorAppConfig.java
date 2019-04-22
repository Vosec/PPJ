package cz.tul.Config;

import cz.tul.Config.ReadOnlySetup.ReadOnlyConditionEnabled;
import cz.tul.Config.ReadOnlySetup.ReadOnlyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//https://www.tutorialspoint.com/spring_boot/spring_boot_interceptor.htm
@Conditional(ReadOnlyConditionEnabled.class)
@Configuration
public class InterceptorAppConfig extends WebMvcConfigurerAdapter {

    private ReadOnlyInterceptor readOnlyInterceptor;

    @Autowired
    public void setReadOnlyInterceptor(ReadOnlyInterceptor readOnlyInterceptor) {
        this.readOnlyInterceptor = readOnlyInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(readOnlyInterceptor);
    }
}
