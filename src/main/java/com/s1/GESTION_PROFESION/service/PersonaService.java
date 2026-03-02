package com.s1.GESTION_PROFESION.service;

import com.s1.GESTION_PROFESION.dto.Request.PersonaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.PersonaResponseDTO;
import java.util.List;

public interface PersonaService {
    PersonaResponseDTO guardarPersona(PersonaRequestDTO dto);
    PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id);
    PersonaResponseDTO buscarPorId(Long id);
    List<PersonaResponseDTO> buscarTodos();
    void eliminarPersona(Long id);
}