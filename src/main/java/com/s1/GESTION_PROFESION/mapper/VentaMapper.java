package com.s1.GESTION_PROFESION.mapper;

import com.s1.GESTION_PROFESION.dto.Response.DetalleResponseDTO;
import com.s1.GESTION_PROFESION.dto.Response.VentaResponseDTO;
import com.s1.GESTION_PROFESION.modelo.Venta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VentaMapper {

    private final DetalleVentaMapper detalleMapper;

    public VentaResponseDTO entidadADTO(Venta venta) {
        // Convertimos la lista de entidades DetalleVenta a DTOs
        List<DetalleResponseDTO> detallesDTO = venta.getDetalles().stream()
                .map(detalleMapper::entidadADTO)
                .toList();

        return new VentaResponseDTO(
                venta.getId(),
                venta.getFecha(),
                venta.getTotal(),
                detallesDTO
        );
    }
}
