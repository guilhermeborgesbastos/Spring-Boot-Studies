package com.gbastos.RESTtemplate.Data;

import org.springframework.stereotype.Component;
import com.gbastos.RESTtemplate.Model.ProductBuilderObject;
import com.gbastos.RESTtemplate.Repository.ProductRepository;
import lombok.AllArgsConstructor;

/**
 * The SampleDataLoader class is used by the AppInit to load into the database one or more Products.
 */
@AllArgsConstructor
@Component
public class SampleDataLoader {

  private ProductRepository productRepository;

  void createSampleData() {
    productRepository.save(ProductBuilderObject.createProduct());
  }
}