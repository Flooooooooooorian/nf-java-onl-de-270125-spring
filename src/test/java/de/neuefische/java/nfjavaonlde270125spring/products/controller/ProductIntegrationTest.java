package de.neuefische.java.nfjavaonlde270125spring.products.controller;

import de.neuefische.java.nfjavaonlde270125spring.products.model.Product;
import de.neuefische.java.nfjavaonlde270125spring.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DirtiesContext
    void getAllProducts() throws Exception {
        //GIVEN
        Product p1 = new Product("1", "Apple", 1.88, 0.99);
        productRepository.save(p1);

        //WHEN
        mockMvc.perform(get("/api/products"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "1",
                                "name": "Apple",
                                "price": 1.88
                            }
                        ]
                        """));
    }

    @Test
    void getProductById() {
    }

    @Test
    @DirtiesContext
    void addProduct() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "id": "dasdfsdfs",
                            "name": "Apple",
                            "price": 3,
                            "buyPrice": 1.99
                        }
                        """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "Apple",
                            "price": 3
                        }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.buyPrice").doesNotExist());


    }

    @Test
    void removeProduct() {
    }

}
