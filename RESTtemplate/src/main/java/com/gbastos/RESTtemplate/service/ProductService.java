package com.gbastos.RESTtemplate.service;

import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.gbastos.RESTtemplate.collection.ProductCollection;
import com.gbastos.RESTtemplate.collection.ProductList;
import com.gbastos.RESTtemplate.exception.EntityNotFoundException;
import com.gbastos.RESTtemplate.handler.RestTemplateResponseErrorHandler;
import com.gbastos.RESTtemplate.model.Product;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Service
public class ProductService {

  static final String EMP_URL_PREFIX = "http://localhost:8080/products";
  static final String URL_SEP = "/";

  private RestTemplate restTemplate;

  @Autowired
  public ProductService(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
  }

  /**
   * Find product by ID, throwing an exception in case that the Entity is not found.
   *
   * @param id
   * @return the optional
   * @throws EntityNotFoundException the entity not found exception
   */
  public Product findById(Long id) throws EntityNotFoundException {

    ResponseEntity<Product> response =
        restTemplate.getForEntity(EMP_URL_PREFIX + URL_SEP + id, Product.class);

    return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
  }

  /**
   * Create a new product, throwing an exception in case that the Entity is already found in the
   * Database.
   *
   * @param product
   * @return the Product object
   */
  public Product create(Product product) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
    map.add(Product.FieldName.NAME, product.getName());
    map.add(Product.FieldName.DESCRIPTION, product.getDescription());
    map.add(Product.FieldName.WEIGHT, product.getWeight());
    map.add(Product.FieldName.ACTIVE, product.getActive());

    HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);

    ResponseEntity<Product> response =
        restTemplate.postForEntity(EMP_URL_PREFIX, request, Product.class);

    return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
  }


  /**
   * Delete the product by ID, throwing an exception in case that the Entity is not found.
   *
   * @param id
   * @throws EntityNotFoundException the entity not found exception
   */
  public void delete(Long id) throws EntityNotFoundException {

    String entityUrl = EMP_URL_PREFIX + "/" + id;
    restTemplate.delete(entityUrl);
  }

  /**
   * Update the product by ID, throwing an exception in case that the Entity is not found or in case
   * of invalid parameters coming from the Client.
   *
   * @param id
   * @param product
   * @return the product
   * @throws EntityNotFoundException the entity not found exception
   */
  public Product update(@Valid Long id, @Valid Product product) throws EntityNotFoundException {

    final HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    final HttpEntity<Product> requestEntity = new HttpEntity<Product>(product, headers);

    final String entityUrl = EMP_URL_PREFIX + "/" + id;

    return restTemplate.exchange(entityUrl, HttpMethod.PUT, requestEntity, Product.class).getBody();
  }

  /**
   * Retrieve multiple Entities based on a collection of Product's IDs, throwing an exception in
   * case that the Entity is not found.
   *
   * @param productCollection the list of product IDs
   * @return the list of Product
   * @throws EntityNotFoundException the entity not found exception
   */
  public List<Product> retrieveCollection(ProductCollection productCollection)
      throws EntityNotFoundException {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
    bodyMap.add(ProductCollection.FieldName.PRODUCT_IDS, productCollection.getProductsIds());

    final String entityUrl = EMP_URL_PREFIX + URL_SEP + "collection";

    ProductList response = restTemplate.getForObject(entityUrl, ProductList.class, bodyMap);

    return response.getProducts();
  }

}
