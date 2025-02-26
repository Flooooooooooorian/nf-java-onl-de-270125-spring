package de.neuefische.java.nfjavaonlde270125spring.products.service;

import de.neuefische.java.nfjavaonlde270125spring.products.exception.NoSuchProductException;
import de.neuefische.java.nfjavaonlde270125spring.products.model.Product;
import de.neuefische.java.nfjavaonlde270125spring.products.repository.ProductRepository;
import de.neuefische.java.nfjavaonlde270125spring.utils.IdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final IdService idService;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No Product found with id " + id);
                    return new NoSuchProductException("No Product found with id " + id);
                });
    }

    public Product save(Product product) {
        String uuid = idService.randomId();
        Product productToSave = product.withId(uuid);

        return productRepository.save(productToSave);
    }

    public void deleteById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else {
            throw new NoSuchElementException("Product with id: " + id + " not found!");
        }
    }
}
