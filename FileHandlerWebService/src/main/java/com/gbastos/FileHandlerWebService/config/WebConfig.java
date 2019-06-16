package com.gbastos.FileHandlerWebService.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan("com.gbastos.FileHandlerWebService")
public class WebConfig implements WebMvcConfigurer {
  
  private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

  @Value("${static.path}")
  private String staticPath;
  
  @Bean
  public ViewResolver internalResourceViewResolver() {
      InternalResourceViewResolver bean = new InternalResourceViewResolver();
      bean.setViewClass(JstlView.class);
      bean.setPrefix("/WEB-INF/view/");
      bean.setSuffix(".jsp");
      return bean;
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("/webjars/");
      
      if(staticPath != null) {
        
        LOG.info("Serving static content from: " + staticPath);
        
        registry
          .addResourceHandler("/**")
          .addResourceLocations("file:" + staticPath);
      }
  }  
}