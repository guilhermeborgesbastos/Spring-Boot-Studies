package com.gbastos.ExceptionHandling.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.gbastos.ExceptionHandling.Model.Product;

/**
 * The ProductRepository class that extends the CrudRepository witch is a Spring Data interface for generic CRUD operations
 * on a repository of a specific type. It provides several methods out of the box for interacting with a database.
 *
 * Read more at - https://www.baeldung.com/spring-data-crud-repository-save
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
