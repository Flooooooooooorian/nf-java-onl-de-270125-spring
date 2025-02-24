package de.neuefische.java.nfjavaonlde270125spring;

import de.neuefische.java.nfjavaonlde270125spring.products.model.Product;
import de.neuefische.java.nfjavaonlde270125spring.products.repository.ProductRepository;
import de.neuefische.java.nfjavaonlde270125spring.products.service.ProductService;
import de.neuefische.java.nfjavaonlde270125spring.utils.IdService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    ProductRepository mockProductRepository = mock(ProductRepository.class);
    IdService mockIdService = mock(IdService.class);
    ProductService productService = new ProductService(mockProductRepository, mockIdService);

    @Test
    void findAll() {
        //GIVEN
        Product p1 = new Product("1", "Test-Produkt-1", 1.5, 1);
        Product p2 = new Product("2", "Test-Produkt-2", 1.5, 1);
        when(mockProductRepository.findAll()).thenReturn(List.of(p1, p2));

        //WHEN
        List<Product> actual = productService.findAll();

        //THEN
        verify(mockProductRepository).findAll();
        List<Product> expected = List.of(p1, p2);
        assertEquals(expected, actual);
    }

    @Test
    void deleteById_successful() {
        //GIVEN
        String id = "1";
        when(mockProductRepository.existsById(id)).thenReturn(true);
        doNothing().when(mockProductRepository).deleteById(id);

        //WHEN
        productService.deleteById(id);

        //THEN
        verify(mockProductRepository).existsById(id);
        verify(mockProductRepository).deleteById(id);
    }

    @Test
    void deleteById_productDoesNotExist() {
        //GIVEN
        String id = "1";
        when(mockProductRepository.existsById(id)).thenReturn(false);

        //THEN//WHEN
        assertThrows(NoSuchElementException.class, () -> productService.deleteById(id));
        verify(mockProductRepository).existsById(id);
    }

    @Test
    void save() {
        //GIVEN
        Product productWithoutId = new Product(null, "test", 0, 0);
        Product productWithId = new Product("13", "test", 0, 0);
        when(mockIdService.randomId()).thenReturn("13");
        when(mockProductRepository.save(productWithId)).thenReturn(productWithId);

        //WHEN
        Product actual = productService.save(productWithoutId);

        //THEN
        verify(mockProductRepository).save(productWithId);
        Product expected = new Product("13", "test", 0, 0);
        assertEquals(expected, actual);
    }
}
