package com.s1.GESTION_PROFESION.controlador;

import com.s1.GESTION_PROFESION.dto.Request.PersonaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.PersonaResponseDTO;
import com.s1.GESTION_PROFESION.service.PersonaService; // Asumiendo que creaste la interfaz
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(personaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PersonaResponseDTO> crear(@Valid @RequestBody PersonaRequestDTO dto) {
        return new ResponseEntity<>(personaService.guardarPersona(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PersonaRequestDTO dto) {
        return ResponseEntity.ok(personaService.actualizarPersona(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}