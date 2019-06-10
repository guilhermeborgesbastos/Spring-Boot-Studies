package com.gbastos.RESTtemplate.service;

import java.util.Arrays;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Service
public class ProductService {

  static final String EMP_URL_PREFIX = "http://localhost:8080/products";
  static final String URL_SEP = "/";

  private RestTemplate restTemplate;
  
  /**
   * Gets the client HTTP request factory configuring the RestTemplate to time out by simply using
   * ClientHttpRequestFactory.
   *
   * @return the client HTTP request factory
   */
  private ClientHttpRequestFactory getClientHttpRequestFactory() {

    int timeout = 5000;
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setConnectTimeout(timeout);

    return clientHttpRequestFactory;
  }

  @Autowired
  public ProductService(RestTemplateBuilder restTemplateBuilder) {
    restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    restTemplate.setRequestFactory(getClientHttpRequestFactory());
  }

  /**
   * Finds product by ID, throwing an exception in case that the Entity is not found.
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
   * Creates a new product, throwing an exception in case that the Entity is already found in the
   * Database.
   *
   * @param product
   * @return the Product object
   */
  public Product create(Product product) {
    
    final HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    final HttpEntity<Product> requestEntity = new HttpEntity<Product>(product, headers);

    ResponseEntity<Product> response =
        restTemplate.exchange(EMP_URL_PREFIX, HttpMethod.POST, requestEntity, Product.class);

    return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
  }

  /**
   * Deletes the product by ID, throwing an exception in case that the Entity is not found.
   *
   * @param id
   * @throws EntityNotFoundException the entity not found exception
   */
  public void delete(Long id) throws EntityNotFoundException {

    String entityUrl = EMP_URL_PREFIX + "/" + id;
    restTemplate.delete(entityUrl);
  }

  /**
   * Updates the product by ID, throwing an exception in case that the Entity is not found or in case
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

    ResponseEntity<Product> response =
        restTemplate.exchange(entityUrl, HttpMethod.PUT, requestEntity, Product.class);

    return response.getStatusCode() == HttpStatus.OK ? response.getBody() : null;
  }
}
