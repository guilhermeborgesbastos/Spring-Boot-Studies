package com.gbastos.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.gbastos.Filter.Filters.RequestResponseLoggingFilter;

/**
 * The filters are registered by default for all the URL’s in our application. However, we may
 * sometimes want a filter to only apply to certain URL patterns.
 * 
 * In this case, we have to remove the @Component annotation from the filter class definition -
 * {link RequestResponseLoggingFilter} and register the filter using a FilterRegistrationBean
 * 
 * Visit the Github page for more details:
 * https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/Filter
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
@Configuration
public class FilterConfig {

  /*
   * Uncomment this and comment the @Component in the filter class
   * (RequestResponseLoggingFilter.java) definition to register only for a URL pattern.
   */
  // @Bean
  public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter() {
    
    FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean =
        new FilterRegistrationBean<>();

    registrationBean.setFilter(new RequestResponseLoggingFilter());

    registrationBean.addUrlPatterns("/users/*");

    return registrationBean;
  }

}
