package de.neuefische.java.nfjavaonlde270125spring.products.controller;

import de.neuefische.java.nfjavaonlde270125spring.products.dto.NewProductDto;
import de.neuefische.java.nfjavaonlde270125spring.products.model.Product;
import de.neuefische.java.nfjavaonlde270125spring.products.service.ProductService;
import de.neuefische.java.nfjavaonlde270125spring.products.dto.ResponseProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    public ProductController(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @GetMapping
    public List<ResponseProductDto> getAllProducts() {
        System.out.println("GET ALL PRODUCTS");
        return productService.findAll().stream()
                .map(product -> new ResponseProductDto(product.id(), product.name(), product.price()))
                .toList();

//        List<ResponseProductDto> responseProductDtos = new ArrayList<>();
//        for (Product product : productService.findAll()) {
//            ResponseProductDto dto = new ResponseProductDto(product.id(), product.name(), product.price());
//            responseProductDtos.add(dto);
//        }
//
//        return responseProductDtos;
    }

    @GetMapping("{id}")
    public ResponseProductDto getProductById(@PathVariable String id) {
        Product product = productService.findById(id);
        return new ResponseProductDto(product.id(), product.name(), product.price());
    }

    @PostMapping
    public ResponseProductDto addProduct(@RequestBody NewProductDto product) {
        Product savedProduct = productService.save(new Product(null, product.name(), product.price(), product.buyPrice()));
        return new ResponseProductDto(savedProduct.id(), savedProduct.name(), savedProduct.price());
    }

    @DeleteMapping("{id}")
    public void removeProduct(@PathVariable String id) {
        productService.deleteById(id);
    }
}
