package com.s1.GESTION_PROFESION.dto.Response;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProfesionResponseDTO(
        @Schema(example = "1")
        Long id,

        @Schema(example = "Ingeniero de Software")
        String nombre,


        @Schema(example = "Encargado del diseño y desarrollo de sistemas complejos.")
        String descripcion
) {
}
