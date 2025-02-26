package de.neuefische.java.nfjavaonlde270125spring.products.dto;

import java.time.LocalDateTime;

public record CustomErrorMessage(
        String message,
        LocalDateTime timestamp
) {
}
