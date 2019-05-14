package com.gbastos.Filter.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gbastos.Filter.Model.User;

/**
 * The Class is a simple REST Controller used to perform tests in the Spring Boot Filter.
 * 
 * Visit the Github page for more details:
 * https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/Filter
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
@RestController
@RequestMapping("/users")
public class UserController {

  private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

  /**
   * Gets the all users created statically.
   *
   * @return the list of all users
   */
  @GetMapping("")
  public List<User> getAllUsers() {

    LOG.info("Fetching all the users");

    return Arrays.asList(new User(UUID.randomUUID().toString(), "User1", "guilherme@test.com"),
        new User(UUID.randomUUID().toString(), "User1", "camilla@test.com"),
        new User(UUID.randomUUID().toString(), "User1", "alexandre@test.com"),
        new User(UUID.randomUUID().toString(), "User1", "gabriel@test.com"),
        new User(UUID.randomUUID().toString(), "User1", "simone@test.com"));
  }

}
