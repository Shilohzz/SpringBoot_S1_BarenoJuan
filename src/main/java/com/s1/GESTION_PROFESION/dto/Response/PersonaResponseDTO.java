package com.s1.GESTION_PROFESION.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record PersonaResponseDTO(
        @Schema(example = "1", description = "ID de la persona")
        Long id,

        @Schema(example = "10203040", description = "Número de identificación")
        String documento,

        @Schema(example = "Juan", description = "Nombre de la persona")
        String nombre,

        @Schema(example = "Pérez", description = "Apellido de la persona")
        String apellido,

        @Schema(example = "28", description = "Edad")
        Integer edad,

        @Schema(example = "2500.50", description = "Salario mensual")
        BigDecimal salario,

        @Schema(description = "Detalle de la profesión asociada")
        ProfesionResponseDTO profesion
) {

}