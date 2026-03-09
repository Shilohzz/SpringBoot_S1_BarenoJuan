package com.s1.GESTION_PROFESION.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDTO(
        @Schema(example = "1")
        Long id,

        @Schema(example = "2026-03-02T11:30:00")
        LocalDateTime fecha,

        @Schema(example = "171.98")
        BigDecimal total,

        List<DetalleResponseDTO> detalles
) {}