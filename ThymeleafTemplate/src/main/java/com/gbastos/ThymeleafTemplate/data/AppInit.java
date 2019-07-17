package com.gbastos.ThymeleafTemplate.data;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The AppInit class implements the Application Runner interface used to execute the code after
 * the Spring Boot application starts.
 */
@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(AppInit.class);

  @Override
  public void run(ApplicationArguments args) {
      
    LOG.info("Setting server time zone to UTC...");
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
