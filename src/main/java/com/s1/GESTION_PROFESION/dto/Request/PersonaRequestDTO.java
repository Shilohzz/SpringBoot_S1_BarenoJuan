package com.s1.GESTION_PROFESION.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PersonaRequestDTO(
        @Schema(example = "10203040", description = "Documento de identidad único")
        String documento,

        @Schema(example = "Juan", description = "Primer y segundo nombre")
        String nombre,

        @Schema(example = "Pérez", description = "Apellidos completos")
        String apellido,

        @NotNull(message = "Error, la edad no puede ser nulo")
        @Schema(example = "28", description = "Edad")
        Integer edad,

        @Schema(example = "2500.50", description = "Salario mensual")
        BigDecimal salario,

        @Schema(example = "1", description = "ID de la profesión")
        Long profesionId
) {
}
