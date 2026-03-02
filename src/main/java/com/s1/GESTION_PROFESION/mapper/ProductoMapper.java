package com.s1.GESTION_PROFESION.mapper;

import com.s1.GESTION_PROFESION.dto.Request.ProductoRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProductoResponseDTO;
import com.s1.GESTION_PROFESION.modelo.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto DTOAEntidad(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        return producto;
    }

    public ProductoResponseDTO entidadADTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio()
        );
    }
}
