package com.gbastos.RESTfulWebServices.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gbastos.RESTfulWebServices.collection.ProductCollection;
import com.gbastos.RESTfulWebServices.exception.EntityNotFoundException;
import com.gbastos.RESTfulWebServices.model.Product;
import com.gbastos.RESTfulWebServices.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  /**
   * Find product by ID, throwing an exception in case that the Entity is not found.
   *
   * @param id the id
   * @return the optional
   * @throws EntityNotFoundException the entity not found exception
   */
  public Optional<Product> findById(Long id) throws EntityNotFoundException {

    Optional<Product> product = productRepository.findById(id);

    if (!product.isPresent()) {
      throw new EntityNotFoundException(Product.class, Product.FieldName.ID, id.toString());
    }

    return product;
  }


/**
   * Create a new product, throwing an exception in case that the Entity is already found in the Database.
   *
   * @param product
   * @return the Product object
   */
  public Product create(Product product) {

    // TODO: improve the Optional usage...
    if(product.getId() != null) {

      Boolean exists = productRepository.findById(product.getId()).isPresent();

      if (exists) {
        throw new DataIntegrityViolationException(String.format("The product with the ID '%s' already exist.", product.getId()));
      }
    }

    return productRepository.save(product);
  }


/**
   * Delete the product by ID, throwing an exception in case that the Entity is not found.
   *
   * @param id the id
   * @throws EntityNotFoundException the entity not found exception
   */
  public void delete(Long id) throws EntityNotFoundException {

    Optional<Product> product = productRepository.findById(id);

    if (!product.isPresent()) {
      throw new EntityNotFoundException(Product.class, Product.FieldName.ID, id.toString());
    }

    productRepository.delete(product.get());
  }

  /**
   * Update the product by ID, throwing an exception in case that the Entity is not found or in case of
   * invalid parameters coming from the Client.
   *
   * @param id the product ID
   * @param product the Product object
   * @return the product
   * @throws EntityNotFoundException the entity not found exception
   */
  public Product update(@Valid Long id, @Valid Product product) throws EntityNotFoundException {

    Product persistedProduct = findById(id).get();

    persistedProduct.setName(product.getName());
    persistedProduct.setDescription(product.getDescription());
    persistedProduct.setWeight(product.getWeight());
    persistedProduct.setActive(product.getActive());

    return productRepository.save(persistedProduct);
  }


/**
   * Retrieve multiple Entities based on a collection of Product's IDs, throwing an exception in case that the Entity is not found.
   *
   * @param productCollection the list of product IDs
   * @return the list of Product
   * @throws EntityNotFoundException the entity not found exception
   */
  public List<Product> retrieveCollection(ProductCollection productCollection) throws EntityNotFoundException {

    List<Product> products = new ArrayList<>();

    for (Long productId : productCollection.getProductsIds()) {

      Optional<Product> product = productRepository.findById(productId);

      if (!product.isPresent()) {
        throw new EntityNotFoundException(Product.class, "id", productId.toString());
      }

      products.add(product.get());
    }

    return products;
  }

  /**
   * Find product by ID, except it doesn’t throw an exception in case that the Entity is not found.
   *
   * @param id
   * @return the Optional<Product>
   * @throws EntityNotFoundException the entity not found exception
   */
  public Optional<Product> findByIdNoException(Long id) throws EntityNotFoundException {
    return productRepository.findById(id);
  }

}