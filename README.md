<img width="250" src="img/spring-by-pivotal.png" align="right" />

# Spring Boot Studies [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> This repository keeps evolving as I continue covering more functionalities and techniques from Spring boot application.

A generic repository for study purposes, with all the exercises from the https://www.tutorialspoint.com/spring_boot

[Get back to the main Summary Page.](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies)


## Spring Boot - Code Structure

Spring Boot does not have any code layout to work with. However, there are some best practices that will help us. This chapter talks about them in detail.

[Read more](https://www.tutorialspoint.com/spring_boot/spring_boot_code_structure.htm)

###  Default package
A class that does not have any package declaration is considered as a default package. Note that generally a default package declaration is not recommended. Spring Boot will cause issues such as malfunctioning of Auto Configuration or Component Scan, when you use default package.

**Note** − Java's recommended naming convention for package declaration is reversed domain name. For example − **com.tutorialspoint.myproject**

###  Typical Layout

The typical layout of Spring Boot application is shown in the image given below −

<img src="img/typical_layout_of_spring_boot_application.jpg " align="center" />

The *Application.java* file should declare the main method along with **@SpringBootApplication**. Observe the code given below for a better understanding −
```
package com.tutorialspoint.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
   public static void main(String[] args) {SpringApplication.run(Application.class, args);}
}
```

## What is the difference between DAO and Repository patterns?


**DAO** is an abstraction of data persistence (*Data Access Object)*. **Repository** is an abstraction of a collection of objects.

*DAO* would be considered closer to the database, often table-centric. *Repository* would be considered closer to the Domain, dealing only in Aggregate Roots. A *Repository* could be implemented using DAO's, but you wouldn't do the opposite.

Also, a *Repository* is generally a narrower **interface**. It should be simply a collection of objects, with a `Get(id)`, `Find(ISpecification)`, `Add(Entity)`. A method like Update is appropriate on a *DAO*, but not a Repository - when using a Repository, changes to entities would usually be tracked by separate UnitOfWork.

It does seem common to see implementations called a Repository that are really more of a DAO, and hence I think there is some confusion about the difference between them.

[Read more](https://stackoverflow.com/a/8550228/6084866)
