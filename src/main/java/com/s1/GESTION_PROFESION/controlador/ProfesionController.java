package com.s1.GESTION_PROFESION.controlador;

import com.s1.GESTION_PROFESION.dto.Request.ProfesionRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProfesionResponseDTO;
import com.s1.GESTION_PROFESION.service.ProfesionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesiones")
@RequiredArgsConstructor
public class ProfesionController {

    private final ProfesionService profesionService;

    @GetMapping
    public ResponseEntity<List<ProfesionResponseDTO>> listarTodas() {
        return ResponseEntity.ok(profesionService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<ProfesionResponseDTO> crear(@Valid @RequestBody ProfesionRequestDTO dto) {
        return new ResponseEntity<>(profesionService.guardarProfesion(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesionResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProfesionRequestDTO dto) {
        return ResponseEntity.ok(profesionService.actualizarProfesion(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        profesionService.eliminarProfesion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProfesionResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(profesionService.buscarNombre(nombre));
    }
}