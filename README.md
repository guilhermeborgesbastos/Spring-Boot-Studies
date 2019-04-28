<img width="250" src="img/spring-by-pivotal.png" align="right" />

# Spring Boot Studies [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)
> This repository keeps evolving as I continue covering more functionalities and techniques from Spring boot application.

A generic repository for study purposes, with all the exercises from the https://www.tutorialspoint.com/spring_boot

[Get back to the main Summary Page.](https://github.com/guilhermeborgesbastos/Spring-Boot-Studies)


# Spring Boot - Interceptor

You can use the Interceptor in Spring Boot to perform operations under the following situations −

* **Before** sending the **request** to the controller;
* **Before** sending the **response** to the client;

For example, you can use an interceptor to add the request header before sending the request to the controller and add the response header before sending the response to the client.

To work with interceptor, you need to create `@Component` class that supports it and it should implement the `HandlerInterceptor` interface.

The following are the three methods you should know about while working on Interceptors −

* `preHandle()` method − *This is used to perform operations before sending the request to the controller. This method should return true to return the response to the client.*

* `postHandle()` method − *This is used to perform operations before sending the response to the client.*

* `afterCompletion()` method − *This is used to perform operations after completing the request and response.*

___


## Getting hands dirty.

In order to better understand it, we are going to exercise the Interceptor concepts in three different applications, they are:

|  Source    | Interceptor    | Implementation Details |
| --------|---------|-------|
| [Read more](http://google.com)   | LoggerInterceptor   |  In this example, we will use Interceptor focusing on logging in a web application.     |
| [Read more](http://google.com)  | TimerInterceptor |  In this example, we will use Interceptor for tracking how long the Request will take in a web application.   |
| [Read more](http://google.com)  | UserInterceptor | It's a more advanced example using interceptors emulating a session timeout logic by setting custom counters and tracking sessions manually.  |