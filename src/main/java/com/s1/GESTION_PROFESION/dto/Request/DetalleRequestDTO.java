package com.s1.GESTION_PROFESION.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;

public record DetalleRequestDTO(
        @Schema(example = "1", description = "ID del producto a vender")
        Long productoId,

        @Schema(example = "2", description = "Cantidad de unidades")
        Integer cantidad
) {}