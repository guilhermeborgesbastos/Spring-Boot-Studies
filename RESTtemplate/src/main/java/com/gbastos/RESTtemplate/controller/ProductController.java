package com.gbastos.RESTtemplate.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gbastos.RESTtemplate.collection.ProductCollection;
import com.gbastos.RESTtemplate.exception.EntityNotFoundException;
import com.gbastos.RESTtemplate.model.Product;
import com.gbastos.RESTtemplate.service.ProductService;

/**
 * The Class ProductServiceController it is a simple sample of a RESTful Web Service used to explain the concept
 * and practice of how to consume external API via RestTemplate on Spring Boot.
 */
@Controller
@RequestMapping("/products")
public class ProductController {
  
  @Autowired
  private ProductService productService;

  @GetMapping(value = "/{id}")
  public Product retrieve(@PathVariable("id") Long id) throws EntityNotFoundException {
    return productService.findById(id);
  }

  @GetMapping(value = "/collection")
  public List<Product> retrieveCollection(@RequestBody ProductCollection productCollection) throws EntityNotFoundException {
    return productService.retrieveCollection(productCollection);
  }

  @GetMapping(value = "/params")
  public Product retrieveRequestParam(@RequestParam(Product.FieldName.ID) Long id) throws EntityNotFoundException {
    return productService.findById(id);
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
