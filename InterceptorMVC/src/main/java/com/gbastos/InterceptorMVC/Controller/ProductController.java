package com.gbastos.InterceptorMVC.Controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gbastos.InterceptorMVC.Model.Product;

/**
 * The Class ProductController it is a simple sample of a RESTful Web Service used to test the
 * concept of Interceptors on Spring Boot.
 *
 * Visit the Github page for more details:
 * https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/Interceptor
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  private static final String ID = "id";

  @GetMapping(value = "/{id}")
  public ResponseEntity<Object> retrieve(@PathVariable(ID) Long id) {
    return new ResponseEntity<>("Product has been fetched successfully. [ LOOK AT THE LOGS ]", HttpStatus.OK);
  }

  @GetMapping(value = "/params")
  public ResponseEntity<Object> retrieveRequestParam(@RequestParam(ID) Long id) {
    return new ResponseEntity<>("Product has been fetched with parameters successfully. [ LOOK AT THE LOGS ]", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody @Valid Product product) {
    return new ResponseEntity<>("Product has been created successfully. [ LOOK AT THE LOGS ]", HttpStatus.OK);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Object> update(@PathVariable(ID) Long id, @RequestBody @Valid Product product) {
    return new ResponseEntity<>("Product has been updated successfully. [ LOOK AT THE LOGS ]", HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> delete(@PathVariable(ID) Long id) {
    return new ResponseEntity<>("Product has been deleted successfully. [ LOOK AT THE LOGS ]", HttpStatus.OK);
  }

}
