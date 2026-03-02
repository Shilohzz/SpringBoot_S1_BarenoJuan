package com.s1.GESTION_PROFESION.service;

import com.s1.GESTION_PROFESION.dto.Request.VentaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.VentaResponseDTO;

import java.util.List;

public interface VentaService {
    VentaResponseDTO registrarVenta(VentaRequestDTO dto);
    List<VentaResponseDTO> listarVentas();
    VentaResponseDTO obtenerVenta(Long id);
}