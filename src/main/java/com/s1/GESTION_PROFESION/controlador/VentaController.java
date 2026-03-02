package com.s1.GESTION_PROFESION.controlador;

import com.s1.GESTION_PROFESION.dto.Request.VentaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.VentaResponseDTO;
import com.s1.GESTION_PROFESION.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> realizarVenta(@RequestBody VentaRequestDTO dto) {
        // Al ejecutar esto, se descuenta stock y se calcula el total automáticamente
        return new ResponseEntity<>(ventaService.registrarVenta(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listarHistorial() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> verDetalleVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVenta(id));
    }
}
