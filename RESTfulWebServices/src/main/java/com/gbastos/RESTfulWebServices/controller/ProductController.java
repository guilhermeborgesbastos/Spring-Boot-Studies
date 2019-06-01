package com.gbastos.RESTfulWebServices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbastos.RESTfulWebServices.model.Product;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gbastos.RESTfulWebServices.collection.ProductCollection;
import com.gbastos.RESTfulWebServices.exception.EntityNotFoundException;
import com.gbastos.RESTfulWebServices.service.ProductService;

/**
 * The Class ProductServiceController it is a simple sample of a RESTful Web Service used to explain the concept
 * and practice of how to handle Exceptions on Spring Boot.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping(value = "/{id}")
  public Product retrieve(@PathVariable("id") Long id) throws EntityNotFoundException {
    return productService.findById(id).get();
  }

  @GetMapping(value = "/collection")
  public List<Product> retrieveCollection(@RequestBody ProductCollection productCollection) throws EntityNotFoundException {
    return productService.retrieveCollection(productCollection);
  }

  @GetMapping(value = "/params")
  public Product retrieveRequestParam(@RequestParam(Product.FieldName.ID) Long id) throws EntityNotFoundException {
    return productService.findById(id).get();
  }

  @GetMapping(value = "/noexception/{id}")
  public Product retrieveNoException(@PathVariable(Product.FieldName.ID) Long id) throws EntityNotFoundException {
    return productService.findByIdNoException(id).get();
  }

  @PostMapping
  public Product create(@RequestBody @Valid Product product) {
    return productService.create(product);
  }
  
  @PutMapping(value = "/{id}")
  public Product update(@PathVariable(Product.FieldName.ID) Long id, @RequestBody @Valid Product product)
          throws EntityNotFoundException {
     return productService.update(id, product);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Object> delete(@PathVariable(Product.FieldName.ID) Long id) throws EntityNotFoundException { 
     productService.delete(id);
     return new ResponseEntity<>("Product has been deleted successfully", HttpStatus.OK);
  }
}