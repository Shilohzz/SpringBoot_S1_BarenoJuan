package com.s1.GESTION_PROFESION.dto.Request;

import java.util.List;

public record VentaRequestDTO(
        List<DetalleRequestDTO> detalles
) {}