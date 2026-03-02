package com.s1.GESTION_PROFESION.controlador;

import com.s1.GESTION_PROFESION.dto.Request.PersonaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.PersonaResponseDTO;
import com.s1.GESTION_PROFESION.service.PersonaService; // Asumiendo que creaste la interfaz
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Personas", description = "Gestión del personal, y sus perfiles profesionales.")
public class PersonaController {

    private final PersonaService personaService;

    @Operation(
            summary = "Listar todas las personas",
            description = "Retorna una lista con todas las personas registradas en la base de datos."
    )
    @GetMapping
    public ResponseEntity<List<PersonaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(personaService.buscarTodos());
    }

    @Operation(
            summary = "Filtrar persona por ID.",
            description = "Obtiene los datos de una persona, mediante el uso de su ID único."
    )
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> obtenerPorId(@Parameter(description = "ID de la persona que desea buscar.", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(personaService.buscarPorId(id));
    }

    @Operation(
            summary = "Registrar una nueva persona",
            description = "Crea una nueva persona y la registra en base de datos. Es obligatorio que le 'profesionId' exista."
    )
    @PostMapping
    public ResponseEntity<PersonaResponseDTO> crear(@Valid @RequestBody PersonaRequestDTO dto) {
        return new ResponseEntity<>(personaService.guardarPersona(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar datos de una persona.",
            description = "Modifica la información de una persona existente mediante su ID único."
    )
    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> actualizar(@Parameter(description = "ID único de la persona que desea actualizar.", example = "1") @PathVariable Long id, @Valid @RequestBody PersonaRequestDTO dto) {
        return ResponseEntity.ok(personaService.actualizarPersona(dto, id));
    }

    @Operation(
            summary = "Eliminar una persona existente.",
            description = "Puede eliminar una persona que existe en base de datos, por medio de su ID único."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID único de la persona para poder eliminarla.", example = "1") @PathVariable Long id) {
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}