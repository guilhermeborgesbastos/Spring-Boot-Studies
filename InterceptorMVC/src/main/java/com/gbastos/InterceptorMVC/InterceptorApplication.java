package com.gbastos.InterceptorMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This projects shows how the Interceptor can be used in three different scenarios:
 * 1st - LoggerInterceptor  =>  In this example, we will use Interceptor focusing on logging in a web application.
 * 2nd - TimerInterceptor   =>  In this example, we will use Interceptor for tracking how long the Request will take in a web application.
 * 3rd - UserInterceptor    =>  It’s a more advanced example using Interceptors emulating a session timeout logic by setting custom counters
 * and tracking sessions manually.
 * 
 * Visit the Github page for more details:
 * https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/Interceptor
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
@SpringBootApplication
public class InterceptorApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterceptorApplication.class, args);
  }

}
