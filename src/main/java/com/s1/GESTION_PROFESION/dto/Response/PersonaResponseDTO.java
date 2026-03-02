package com.s1.GESTION_PROFESION.dto.Response;

import java.math.BigDecimal;

public record PersonaResponseDTO(
        Long id, String documento, String nombre, String apellido,
        Integer edad, BigDecimal salario, ProfesionResponseDTO profesion
) {

}