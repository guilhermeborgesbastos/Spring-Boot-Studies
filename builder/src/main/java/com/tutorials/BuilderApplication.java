package com.tutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.model.Computer;

@SpringBootApplication
@RestController
public class BuilderApplication extends SpringBootServletInitializer { // It is needed to extend the class SpringBootServletInitializer to support WAR file deployment.

   public static void main(String[] args) {
      SpringApplication.run(BuilderApplication.class, args);
   }

   @RequestMapping(value = "/")
   public String hello() throws JsonProcessingException {
  
	   ObjectMapper objectMapper = new ObjectMapper();

	   /*
	    * Using builder to get the object in a single line of code and
	    * without any inconsistent state or arguments management issues. 
	    */		
	   Computer comp = new Computer.ComputerBuilder("500 GB", "2 GB").setBluetoothEnabled(true).setGraphicsCardEnabled(true).build();
	   return objectMapper.writeValueAsString(comp);
   }
   
   /**
    * (non-Javadoc)
    * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
    */
   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(BuilderApplication.class);
   }
}