package de.neuefische.java.nfjavaonlde270125spring.products.model;

import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@With
public record Product(
        @Id
        String id,
        String name,
        double price,
        double buyPrice) {
}
