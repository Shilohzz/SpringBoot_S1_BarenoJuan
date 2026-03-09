package com.s1.GESTION_PROFESION.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record ProductoRequestDTO(
        @Schema(example = "Teclado Mecánico", description = "Lo último en guaracha INC")
        String nombre,

        @Schema(example = "85.99", description = "Precio de venta")
        BigDecimal precio,

        @Schema(example = "50", description = "Stock")
        Integer stock
) {}