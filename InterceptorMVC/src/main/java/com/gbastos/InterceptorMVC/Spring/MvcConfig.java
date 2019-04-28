package com.gbastos.InterceptorMVC.Spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gbastos.InterceptorMVC.Interceptors.TimerInterceptor;

/**
 * The Class MvcConfig adds our Interceptors into Spring configuration, we need to override
 * addInterceptors() method inside WebConfig class that implements WebMvcConfigurer. We may achieve
 * the same configuration by editing our XML Spring configuration file.
 * 
 * NOTE - If multiple Spring Interceptors are configured, the preHandle() method is executed in the
 * order of configuration, whereas postHandle() and afterCompletion() methods are invoked in the
 * reverse order.
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.gbastos.InterceptorMVC.Controller")
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        super();
    }

    /**
     * Adds the Interceptors in the desired ordering.
     *
     * @param registry
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new TimerInterceptor());
    }
}