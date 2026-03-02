package com.s1.GESTION_PROFESION.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductoResponseDTO(
        @Schema(example = "1")
        Long id,

        @Schema(example = "Teclado Mecánico RGB")
        String nombre,

        @Schema(example = "85.99")
        Double precio
) {}