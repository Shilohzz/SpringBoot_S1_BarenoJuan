package com.s1.GESTION_PROFESION.service;

import com.s1.GESTION_PROFESION.dto.Request.ProfesionRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProfesionResponseDTO;

import java.util.List;

public interface ProfesionService {
    ProfesionResponseDTO guardarProfesion(ProfesionRequestDTO dto);
    ProfesionResponseDTO actualizarProfesion(ProfesionRequestDTO dto, Long id);
    void eliminarProfesion(Long id);
    List<ProfesionResponseDTO> buscarTodos();
    List<ProfesionResponseDTO> buscarNombre(String nombre);

}
