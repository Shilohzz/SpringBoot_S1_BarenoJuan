package com.s1.GESTION_PROFESION.service.impl;

import com.s1.GESTION_PROFESION.Exception.BusinessRuleException;
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

import java.math.BigDecimal;
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
            Producto producto = productoRepository.findById(detalleDto.productoId())
                    .orElseThrow(() -> new BusinessRuleException("Producto con ID " + detalleDto.productoId() + " no existe"));

            if (producto.getStock() < detalleDto.cantidad()) {
                throw new BusinessRuleException("Stock insuficiente para: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - detalleDto.cantidad());

            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDto.cantidad());
            detalle.setPrecioUnitario(producto.getPrecio());

            // Multiplico precio * cantidad usando BigDecimal para no perder precisión decimal
            BigDecimal cantidad = BigDecimal.valueOf(detalleDto.cantidad());
            detalle.setSubtotal(producto.getPrecio().multiply(cantidad));
            detalle.setVenta(venta);
            return detalle;
        }).collect(Collectors.toList());

        venta.setDetalles(detalles);

        // Sumo todos los subtotales con reduce para obtener el total de la venta
        BigDecimal total = detalles.stream()
                .map(DetalleVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        venta.setTotal(total);

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
                .orElseThrow(() -> new BusinessRuleException("Venta con ID " + id + " no encontrada"));
    }
}