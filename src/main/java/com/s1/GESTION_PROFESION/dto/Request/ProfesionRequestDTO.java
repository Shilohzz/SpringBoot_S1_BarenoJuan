package com.s1.GESTION_PROFESION.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * EL record, permite ser llenado por constructor,
 * se puede acceder a sus datos, directamente por el nombre de la clase.
 * Con esto evitamos los getter, setters y demas validaciones. Centrandonos
 * unicamente en transferencia y obtención de datos.
 * */

public record ProfesionRequestDTO(
        @Schema(example = "Ingeniero de Software", description = "Profesional Certificado en Ingenieria de Software")
        @NotBlank(message = "El nombre no puede estar vacio.")
        @Size(min = 2, max = 50, message = "Error, el rango del nombre debe estar entre 2 y 50 caracteres")
        String nombre,
        @NotBlank(message = "El nombre no puede estar vacio.")
        @Size(min = 2, max = 50, message = "Error, el rango del nombre debe estar entre 2 y 50 caracteres")
        @Schema(example = "Encargado del equipo de desarrollo", description = "Lidera y coordina el equipo de desarrollo")
        String descripcion
) { }
