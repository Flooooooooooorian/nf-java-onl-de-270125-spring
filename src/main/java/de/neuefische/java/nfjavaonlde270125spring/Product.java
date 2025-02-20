package de.neuefische.java.nfjavaonlde270125spring;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public record Product(
        @Id
        String id,
        String name,
        double price) {
}
