<img width="250" src="img/spring-by-pivotal.png" align="right" />

# Spring Boot Studies [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> This repository keeps evolving as I continue covering more functionalities and techniques from Spring boot application.

A generic repository for study purposes, with all the exercises from the https://www.tutorialspoint.com/spring_boot

[Get back to the main Summary Page.](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies)


# Spring Boot - Runners

## What is a Application Runner for Spring Boot?
*Application Runner* and *Command Line Runner* **interfaces** lets you to execute the code after the Spring Boot application is started. You can use these interfaces to perform any actions immediately after the application has started. This chapter talks about them in detail.


## Application Runner

*Application Runner* is an interface used to execute the code after the Spring Boot application started. The example given below shows how to implement the Application Runner interface on the main class file.
```
package com.gbastos.springboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class App implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String... args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
        logger.info("OptionNames: {}", args.getOptionNames());

        for (String name : args.getOptionNames()){
            logger.info("arg-" + name + "=" + args.getOptionValues(name));
        }

        Boolean containsOption = args.containsOption("person.name");
        logger.info("Contains person.name: " + containsOption);
    }
}
```

The overriding *run()* method writes in the log info regarding:

* **The command-line arguments sent in the application start**
The parameter can be sent to Spring Boot via command line:
```
java -jar target/command-line.jar this-is-a-non-option-arg --server.port=9090 --person.name=Guilherme --person.employer=QAT Global 
```
In case of the existence of the `application.properties` file located in the `src/main/resources` folder, the default properties will be overridden by the ones sent in the command line. For example:
```
person.name=Default Name Parameter
person.employer=Default Employer Parameter
```

There is also non optional argument, in the example above is the `this-is-a-non-option-arg`, look at the Spring Boot terminal logging of this example:

<img src="img/Application-Runner-Args-Output-Terminal.png" align="center" />