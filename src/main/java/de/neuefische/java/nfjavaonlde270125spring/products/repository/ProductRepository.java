package de.neuefische.java.nfjavaonlde270125spring.products.repository;

import de.neuefische.java.nfjavaonlde270125spring.products.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
