<img width="250" src="../img/spring-by-pivotal.png" align="right" />

# Spring Boot Studies [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> This repository keeps evolving as I continue covering more functionalities and techniques from Spring boot application.

A generic repository for study purposes, with all the exercises from the https://www.tutorialspoint.com/spring_boot

[Get back to the main Summary Page.](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies)


# Dependency Injection, IoC, Beans and Bean Scopes

## What is Dependency Injection
It’s the process of decoupling classes from what they depend on.
``` 
public class Customer {
    // Dependency
    Service service = new Service(“CoolService”)
}
```

The **Service** object is a dependency, the reason to call it as a dependency is because the *Customer* Object/Class **depends on** this *Service* Object in order to function properly.

## Why DI(*Dependency Injection*) is important?
The main reasons why *Dependency Injection* is important are:

*  **Inversion of Control (IoC)**
Classes should configure their dependencies from the outside;

* **Java Classes should be as independent as possible from other classes**
Increases the possibility of **Reusing these classes** and **Testing** these classes **independently from other classes**;

## Ways of Injecting Dependencies
There are the following ways of injecting dependencies:

*  By **Constructors**;
*  By **Setters methods**;
*  By **methods**;

## What is a bean?
A *bean* is basically an object that is managed by an *IoC container* (*Spring IoC container*).

## What is a IoC container?
It is a Framework used to create and inject dependencies automatically.

### Types of IoC containers

* **Bean Factory**
It's deprecated, it uses simple container used for DI -
```
XmlBeanFactory factory = 
            new XmlBeanFactory( new ClassPathResource(“Beans.xml”));
```

* **Application Context**
It's as advanced container used for DI
```
ApplicationContect context = 
             new FileSystemXmlApplicationContext(“Beans.xml”);
```

## Beans and Dependency Injection on *Spring Boot*

In Spring Boot, we can use Spring Framework to define our *beans* and their *dependency injection*. The **@ComponentScan** annotation is used to find beans and the corresponding injected with **@Autowired** annotation.

If you followed the Spring Boot typical layout, no need to specify any arguments for **@ComponentScan** annotation. All component class files are automatically registered with Spring Beans.

The following example provides an idea about Auto wiring the Rest Template object and creating a Bean for the same −
```
@Bean
public InjectDependency getInjectDependency() {
    return new InjectDependency("SSD WD Green 2.5 240GB SATA III", 240D);   
}
```
The following code shows the code for auto wired Rest Template object and Bean creation object in main Spring Boot Application class file −
```
package com.tutorials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.tutorials.model.InjectDependency;

@SpringBootApplication
public class BuilderApplication {

   @Autowired
   InjectDependency injectDependency;
   
   public static void main(String[] args) {
      SpringApplication.run(BuilderApplication.class, args);
   }

   @Bean
   public InjectDependency getInjectDependency() {
      return new InjectDependency("SSD WD Green 2.5 240GB SATA III", 16D);   
   }
   
}
```

See more complete example in the repository code.

[Read more](https://www.tutorialspoint.com/spring_boot/spring_boot_beans_and_dependency_injection.htm)


### What are bean scopes?

Determines the type of bean instance that should be returned.

* **Singleton** *(Widely used)*
Return single bean instance per Spring IoC container;
* **Prototype** *(Widely used)*
Return a new bean instance each time when requested;
* **Request** 
Return a single bean instance per HTTP requested;
* **Session**
Return a single bean instance per HTTP session;
* **Global Session**
Return a single bean instance per global HTTP session;

[Read more](https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch04s04.html)

## Difference between Singleton and Prototype scopes?

### Singleton
Return single bean instance per Spring IoC container

This configuration is done in the *Beans.xml* file.
```
<bean id="dog" class="com.gui.spring.SpringProj.Dog" scope=”singleton”>
</bean>
```

In the example below we have a simple sample of its usage:

```
public class App
{
   public static void main ( String[] args)
   {
      ApplicationContect context = 
             new FileSystemXmlApplicationContext(“beans.xml”);
      Dog dog = (Dog) context.getBean(Dog.class);
      
      // Setting a specific value to a property of the class
      dog.setBark(“Woof Woof”);

      // Printing the property value of the dog object
      System.out.printLn(dog.getBark());

      // Retrieve it again, within a ‘new’ object
      Dog dog2 = (Dog) context.getBean(“dog”);

      // Printing the property value of dog2 object
      System.out.printLn(dog2.getBark());
   }
}
```

This is the output of the code above:

```
Woof Woof
Woof Woof
```

Since we are using the singleton scope for this bean, by retrieving it again its gonna return a single bean instance.

### Prototype
Return a new bean instance each time when requested;

This configuration is done in the Beans.xml file.
```
<bean id="dog" class="com.gui.spring.SpringProj.Dog" scope=”prototype”>
</bean>
```

In the example below we have a simple sample of its usage:
 
```
public class App
{
   public static void main ( String[] args)
   {
      ApplicationContect context = 
             new FileSystemXmlApplicationContext(“beans.xml”);
      Dog dog = (Dog) context.getBean(Dog.class);
      
      // Setting a specific value to a property of the class
      dog.setBark(“Woof Woof”);

      // Printing the property value of the dog object
      System.out.printLn(dog.getBark());

      // Retrieve it again, within a new instance
      Dog dog2 = (Dog) context.getBean(“dog”);

      // Printing the property value of dog2 object
      System.out.printLn(dog2.getBark());
   }
}
```

This is the output of the code above:
```
Woof Woof
null
```

Since we are using the singleton scope for this bean, by retrieving it again its gonna return a single bean instance.

[Read more](https://www.tutorialspoint.com/spring/spring_bean_scopes.htm)

## Troubleshooting

This topic will grow according to the issues and solutions regarding the usage of DI, IoC, Beans, etc... For now it's empty.
