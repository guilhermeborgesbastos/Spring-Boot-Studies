package com.gbastos.RESTtemplate.collection;

import java.util.ArrayList;
import java.util.List;
import com.gbastos.RESTtemplate.model.Product;
import lombok.Data;

@Data
public class ProductList {
  
  private List<Product> products;
  
  public ProductList() {
    products = new ArrayList<>();
  }
}
