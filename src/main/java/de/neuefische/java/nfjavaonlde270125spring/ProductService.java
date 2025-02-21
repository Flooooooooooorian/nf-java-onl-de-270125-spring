package de.neuefische.java.nfjavaonlde270125spring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    public Product save(Product product) {
        String uuid = UUID.randomUUID().toString();
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
