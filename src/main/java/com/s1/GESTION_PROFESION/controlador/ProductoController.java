package com.s1.GESTION_PROFESION.controlador;

import com.s1.GESTION_PROFESION.dto.Request.ProductoRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProductoResponseDTO;
import com.s1.GESTION_PROFESION.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Gestión de inventario y precios.")
public class ProductoController {

    private final ProductoService productoService;

    @Operation(
            summary = "Registrar nuevo producto.",
            description = "Crea un nuevo producto con toda su información y la registra en base de datos."
    )
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@RequestBody ProductoRequestDTO dto) {
        return new ResponseEntity<>(productoService.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener catálogo completo",
            description = "Devuelve una lista con todos los productos disponibles."
    )
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @Operation(
            summary = "Filtrar producto por ID.",
            description = "Permite filtrar y encontrar la información completa de un producto en específico, mediante su ID único."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> buscar(@Parameter(description = "ID único del producto", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }
}
