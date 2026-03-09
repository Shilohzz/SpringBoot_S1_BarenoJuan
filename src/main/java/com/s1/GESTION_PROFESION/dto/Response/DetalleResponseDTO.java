package com.s1.GESTION_PROFESION.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public record DetalleResponseDTO(
        @Schema(example = "10")
        Long id,

        @Schema(example = "Teclado Mecánico RGB")
        String productoNombre,

        @Schema(example = "2")
        Integer cantidad,

        @Schema(example = "171.98")
        BigDecimal subtotal
) {}