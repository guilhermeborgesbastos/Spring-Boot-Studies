package com.gbastos.RESTfulWebServices.data;

import org.springframework.stereotype.Component;
import com.gbastos.RESTfulWebServices.model.ProductBuilderObject;
import com.gbastos.RESTfulWebServices.repository.ProductRepository;
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