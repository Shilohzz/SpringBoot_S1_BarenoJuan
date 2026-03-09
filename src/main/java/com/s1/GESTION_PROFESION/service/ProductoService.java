package com.s1.GESTION_PROFESION.service;

import com.s1.GESTION_PROFESION.dto.Request.ProductoRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    ProductoResponseDTO guardar(ProductoRequestDTO dto);
    List<ProductoResponseDTO> listarTodos();
    ProductoResponseDTO buscarPorId(Long id);
    ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id);
    void eliminarProducto(Long id);
}