package com.s1.GESTION_PROFESION.controlador;

import com.s1.GESTION_PROFESION.dto.Request.ProfesionRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProfesionResponseDTO;
import com.s1.GESTION_PROFESION.service.ProfesionService;
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
@RequestMapping("/api/profesiones")
@RequiredArgsConstructor
@Tag(name = "Profesiones", description = "Operaciones para el catálogo de cargos y/o profesiones")
public class ProfesionController {

    private final ProfesionService profesionService;

    @Operation(
            summary = "Listar todas las profesiones",
            description = "Retorna una lista de todas las profesiones registradas en base de datos"
    )
    @GetMapping
    public ResponseEntity<List<ProfesionResponseDTO>> listarTodas() {
        return ResponseEntity.ok(profesionService.buscarTodos());
    }


    @Operation(
            summary = "Registrar una nueva profesión",
            description = "Crea una nueva profesión y la guarda en base de datos. (El nombr de la profesión debe ser único)"
    )
    @PostMapping
    public ResponseEntity<ProfesionResponseDTO> crear(@Valid @RequestBody ProfesionRequestDTO dto) {
        return new ResponseEntity<>(profesionService.guardarProfesion(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar una profesión existente.",
            description = "Puedes actualizar una profesión que ya exista en base de datos, mediante el ID único."
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProfesionResponseDTO> actualizar(@Parameter(description = "ID único de la profesión a actualizar.", example = "1") @PathVariable Long id, @Valid @RequestBody ProfesionRequestDTO dto) {
        return ResponseEntity.ok(profesionService.actualizarProfesion(dto, id));
    }

    @Operation(
            summary = "Eliminar una profesión existente.",
            description = "Puedes eliminar una profesión existente en base de datos, mediante el ID único."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@Parameter(description = "ID de la profesión que se desea eliminar.", example = "1") @PathVariable Long id) {
        profesionService.eliminarProfesion(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar profesiones por nombre.",
            description = "Puedes filtrar las profesiones por medio de su nombre único"
    )
    @GetMapping("/buscar")
    public ResponseEntity<List<ProfesionResponseDTO>> buscarPorNombre(@Parameter(description = "Nombre de la profesión a buscar.", example = "Ingeniero de Software") @RequestParam String nombre) {
        return ResponseEntity.ok(profesionService.buscarNombre(nombre));
    }
}