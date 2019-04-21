package com.gbastos.ExceptionHandling.Collection;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class is used as container for the Product's IDs that are going to be fetched from the
 * database. Also contains some basic validations in order to work properly.
 */
public class ProductCollection {

  @NotNull
  @Size(min = 2) 	 // Should have at least 2 items (IDs) in the Array
  private List<Long> productsIds;

  public List<Long> getProductsIds() {
    return productsIds;
  }

  public void setProductsIds(List<Long> productsIds) {
    this.productsIds = productsIds;
  }
}
