package com.gbastos.ThymeleafTemplate.config;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.gbastos.ThymeleafTemplate.utils.ArrayUtil;


@EnableWebMvc
@Configuration
@ComponentScan({"com.gbastos.ThymeleafTemplate"})
/**
 * Java configuration file that is used for Spring MVC and Thymeleaf configurations
 */
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

  private static final Logger LOG = LoggerFactory.getLogger(WebConfig.class);

  private ApplicationContext applicationContext;
  
  @Value("${static.path}")
  private String staticPath;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  /**
   * Sets the HTML template resolver among with its configurations.
   *
   * @return the template resolver
   */
  private ITemplateResolver htmlTemplateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/view/");
    resolver.setCacheable(false);
    resolver.setTemplateMode(TemplateMode.HTML);
    return resolver;
  }

  /**
   * Sets the Javascript template resolver among with its configurations.
   *
   * @return the template resolver
   */
  private ITemplateResolver javascriptTemplateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/js/");
    resolver.setCacheable(false);
    resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
    return resolver;
  }

  /**
   * Created the Template Engine instance to be used for processing templates.
   *
   * @return the  Sub-interface of {@link ITemplateEngine}
   */
  private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.addDialect(new LayoutDialect(new GroupingStrategy()));
    engine.addDialect(new Java8TimeDialect());
    engine.setTemplateResolver(templateResolver);
    engine.setTemplateEngineMessageSource(messageSource());
    return engine;
  }

  /**
   * Sets the Thymeleaf's view resolver for the HTML files.
   *
   * @return the view resolver
   */
  @Bean
  public ViewResolver htmlViewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
    resolver.setContentType("text/html");
    resolver.setCharacterEncoding("UTF-8");
    resolver.setViewNames(ArrayUtil.array("*.html"));
    return resolver;
  }
  
  /**
   * Sets the Thymeleaf's view resolver for the Javascript files.
   *
   * @return the view resolver
   */
  @Bean
  public ViewResolver javascriptViewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine(javascriptTemplateResolver()));
    resolver.setContentType("application/javascript");
    resolver.setCharacterEncoding("UTF-8");
    resolver.setViewNames(ArrayUtil.array("*.js"));
    return resolver;
  }
  
  @Bean
  @Description("Spring Message Resolver")
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(new Locale("en"));
    return localeResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    return localeChangeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  /**
   * Add handlers to serve static resources such as images, js, and, css files from specific
   * locations under web application root, the classpath, and others.
   *
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    if (staticPath != null) {
      LOG.info("Serving static content from: " + staticPath);
      registry.addResourceHandler("/**").addResourceLocations("file:" + staticPath);
    }
  }
}
