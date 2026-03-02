package com.s1.GESTION_PROFESION.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductoRequestDTO(
        @Schema(example = "Teclado Mecánico", description = "Lo último en guaracha INC")
        String nombre,

        @Schema(example = "85.99", description = "Precio de venta")
        Double precio,

        @Schema(example = "50", description = "Stock")
        Integer stock
) {}