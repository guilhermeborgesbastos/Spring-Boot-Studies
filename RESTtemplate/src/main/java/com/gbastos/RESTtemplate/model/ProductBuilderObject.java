package com.gbastos.RESTtemplate.model;

import org.springframework.stereotype.Component;

/**
 *  The ProductBuilderObject class holds the static method createProduct used to create a predefined Product object
 *  to be saved in the database during  the application initialization. 
 */
@Component
public class ProductBuilderObject {

  public static Product createProduct() { 
	  
    return Product.builder()
        .name("Asus Prime B250M-Plus/BR, Intel LGA 1151, mATX, DDR4")
        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
        .weight(456)
        .active(true)
        .build();
  }

}
