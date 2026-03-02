package com.s1.GESTION_PROFESION.service.impl;

import com.s1.GESTION_PROFESION.dto.Request.VentaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.VentaResponseDTO;
import com.s1.GESTION_PROFESION.mapper.VentaMapper;
import com.s1.GESTION_PROFESION.modelo.DetalleVenta;
import com.s1.GESTION_PROFESION.modelo.Producto;
import com.s1.GESTION_PROFESION.modelo.Venta;
import com.s1.GESTION_PROFESION.repository.ProductoRepository;
import com.s1.GESTION_PROFESION.repository.VentaRepository;
import com.s1.GESTION_PROFESION.service.VentaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final VentaMapper ventaMapper;

    @Override
    @Transactional
    public VentaResponseDTO registrarVenta(VentaRequestDTO dto) {
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());

        List<DetalleVenta> detalles = dto.detalles().stream().map(detalleDto -> {
            // 1. Validar producto
            Producto producto = productoRepository.findById(detalleDto.productoId())
                    .orElseThrow(() -> new RuntimeException("Producto ID " + detalleDto.productoId() + " no existe"));

            // 2. Validar y descontar Stock
            if (producto.getStock() < detalleDto.cantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
            }
            producto.setStock(producto.getStock() - detalleDto.cantidad());

            // 3. Crear el detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDto.cantidad());
            detalle.setPrecioUnitario(producto.getPrecio()); // Guardamos el precio del momento
            detalle.setSubtotal(producto.getPrecio() * detalleDto.cantidad());
            detalle.setVenta(venta);
            return detalle;
        }).collect(Collectors.toList());

        venta.setDetalles(detalles);
        venta.setTotal(detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum());

        return ventaMapper.entidadADTO(ventaRepository.save(venta));
    }

    @Override
    public List<VentaResponseDTO> listarVentas() {
        return ventaRepository.findAll().stream()
                .map(ventaMapper::entidadADTO).toList();
    }

    @Override
    public VentaResponseDTO obtenerVenta(Long id) {
        return ventaRepository.findById(id)
                .map(ventaMapper::entidadADTO)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }
}
