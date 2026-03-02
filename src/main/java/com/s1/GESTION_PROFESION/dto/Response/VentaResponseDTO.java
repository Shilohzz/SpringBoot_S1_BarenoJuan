package com.s1.GESTION_PROFESION.dto.Response;

import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDTO(Long id, LocalDateTime fecha, Double total, List<DetalleResponseDTO> detalles) {}
