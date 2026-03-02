package com.s1.GESTION_PROFESION.controlador;

import com.s1.GESTION_PROFESION.dto.Request.VentaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.VentaResponseDTO;
import com.s1.GESTION_PROFESION.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "Operaciones de procesamiento de pedidos, control de stock y facturación")
public class VentaController {

    private final VentaService ventaService;

    @Operation(
            summary = "Procesar una nueva venta",
            description = "Registra una transacción de venta. Internamente descuenta el stock de los productos, " +
                    "calcula subtotales por línea y genera el total general de la factura."
    )
    @PostMapping
    public ResponseEntity<VentaResponseDTO> realizarVenta(@RequestBody VentaRequestDTO dto) {
        return new ResponseEntity<>(ventaService.registrarVenta(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Consultar historial de ventas",
            description = "Obtiene una lista cronológica de todas las ventas realizadas en el sistema."
    )
    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listarHistorial() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @Operation(
            summary = "Ver detalle de una venta específica",
            description = "Retorna la información completa de una venta (fecha, total) junto con el desglose de productos vendidos."
    )
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> verDetalleVenta(
            @Parameter(description = "ID de la venta a consultar", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVenta(id));
    }
}
