package com.s1.GESTION_PROFESION.service.impl;

import com.s1.GESTION_PROFESION.Exception.BusinessRuleException;
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
                .orElseThrow(() -> new BusinessRuleException("Producto con ID " + id + " no encontrado"));
    }

    @Override
    @Transactional
    public ProductoResponseDTO actualizarProducto(ProductoRequestDTO dto, Long id) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Producto con ID " + id + " no encontrado"));

        productoMapper.actualizarEntidadDesdeDTO(productoExistente, dto);
        return productoMapper.entidadADTO(productoRepository.save(productoExistente));
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new BusinessRuleException("No se puede eliminar: Producto con ID " + id + " no existe");
        }
        productoRepository.deleteById(id);
    }
}