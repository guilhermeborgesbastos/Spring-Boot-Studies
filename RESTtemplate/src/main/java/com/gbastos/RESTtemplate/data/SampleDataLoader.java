package com.gbastos.RESTtemplate.data;

import org.springframework.stereotype.Component;
import com.gbastos.RESTtemplate.model.ProductBuilderObject;
import com.gbastos.RESTtemplate.repository.ProductRepository;
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