package com.s1.GESTION_PROFESION.service.impl;

import com.s1.GESTION_PROFESION.dto.Request.ProductoRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProductoResponseDTO;
import com.s1.GESTION_PROFESION.mapper.ProductoMapper;
import com.s1.GESTION_PROFESION.modelo.Producto;
import com.s1.GESTION_PROFESION.repository.ProductoRepository;
import com.s1.GESTION_PROFESION.service.ProductoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional
    public ProductoResponseDTO guardar(ProductoRequestDTO dto) {
        Producto producto = productoMapper.DTOAEntidad(dto);
        return productoMapper.entidadADTO(productoRepository.save(producto));
    }

    @Override
    public List<ProductoResponseDTO> listarTodos() {
        return productoRepository.findAll().stream()
                .map(productoMapper::entidadADTO).toList();
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::entidadADTO)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
