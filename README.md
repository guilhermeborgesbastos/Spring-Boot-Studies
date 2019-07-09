<img width="250" src="img/spring-by-pivotal.png" align="right" />

# Spring Boot Studies [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> This repository keeps evolving as I continue covering more functionalities and techniques from Spring boot application.

A generic repository for study purposes, with all the exercises from the https://www.tutorialspoint.com/spring_boot

[Get back to the main Summary Page.](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies)


# Spring Boot - Service Components

## Service layer pattern
`Service Layer` is a *design pattern*, applied within the *service-orientation design paradigm*, which aims to organize the services, within a service inventory, into a set of logical layers. Services that are categorized into a particular layer share functionality. This helps to reduce the conceptual overhead related to managing the service inventory, as the services belonging to the same layer address a smaller set of activities. 

### Rationale
Grouping services into functional layers reduces the impact of change. Most changes affect only the layer in which they're made, with few side-effects that impact other layers. This fundamentally simplifies service maintenance.

The *service reusability principle* dictates that services should be designed to maximize reuse. Similarly, the *service composability principle* advocates designing services so that they can be composed in various ways. Both principles require that a service contain only a specific type of logic. Restricting each layer to a particular functionality simplifies the design of the service.

## What is a @Service component?

Service Components are the class file which contains @Service annotation. These class files are used to write business logic in a different layer, separated from @RestController class file. 

In other words, the `Service` layer is the middle layer between **presentation** and **data store**. It abstracts business logic and data access. The idea behind such a layer is to have an architecture which can support multiple presentation layers such as web, mobile, etc. Mostly it has a separate physical tier of its own to cleanly segregate it with any presentation layer. This provides easier management, better abstraction, and scalability supporting a large number of simultaneous clients.

## What makes a "*GOOD*" @Service layer?

To make it as simple as possible, let's present, in a general view, what makes a *'good'* Service Layer. We could focus on some concerns such as:

* Centralizes external access to data and functions;
> By using the *Data access logic components*, it abstracts the logic necessary to access your underlying data stores. Doing so centralizes the data access functionality, which makes the application easier to configure and maintain. 
* Hides (abstracts) internal implementation and changes;
> The Abstraction it's a well known OOP concept which focuses on relevant information by hiding unnecessary detail. That is basically what a `@Service` layer is doing by offering services that can be easily used. It lets you focus on what the object does instead of how it does.
* Allows for versioning of the services;
> Nowadays an API has to satisfy several clients simultaneously. By versioning your services, it becomes easier to guaranteed to not break client integration when a version is updated.

## Getting hands dirty

We've been exercising the usage of `@Service` layers for many of our samples on this repository. With the theory right above, let's revisit our last project that was created based on the *Service Component Architecture *- [File Handling](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/FileHandling) topic.

There, we have two `@Service` classes:

* The [FileUploadService](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/blob/FileHandling/FileHandlerWebService/src/main/java/com/gbastos/FileHandlerWebService/service/FileUploadService.java) class;
* The [FileDownloadService](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/blob/FileHandling/FileHandlerWebService/src/main/java/com/gbastos/FileHandlerWebService/service/FileDownloadService.java) class;

Both services have their respective *controllers* that receives via Dependency Injection(DI) the services with the abstraction needed to keep its usage simple and straightforward.


