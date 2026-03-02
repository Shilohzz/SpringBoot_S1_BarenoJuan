package com.s1.GESTION_PROFESION.dto.Request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PersonaRequestDTO(
        String documento,
        String nombre,
        String apellido,
        @NotNull(message = "Error, la edad no puede ser nulo")
        Integer edad,
        BigDecimal salario,
        Long profesionId
) {
}
