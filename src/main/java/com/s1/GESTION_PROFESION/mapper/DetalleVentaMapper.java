package com.s1.GESTION_PROFESION.mapper;

import com.s1.GESTION_PROFESION.dto.Response.DetalleResponseDTO;
import com.s1.GESTION_PROFESION.modelo.DetalleVenta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {

    public DetalleResponseDTO entidadADTO(DetalleVenta detalle) {
        return new DetalleResponseDTO(
                detalle.getId(),
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }
}