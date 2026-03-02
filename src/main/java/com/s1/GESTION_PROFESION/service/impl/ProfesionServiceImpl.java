package com.s1.GESTION_PROFESION.service.impl;

import com.s1.GESTION_PROFESION.dto.Request.ProfesionRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProfesionResponseDTO;
import com.s1.GESTION_PROFESION.mapper.ProfesionMapper;
import com.s1.GESTION_PROFESION.modelo.Profesion;
import com.s1.GESTION_PROFESION.repository.ProfesionRepository;
import com.s1.GESTION_PROFESION.service.ProfesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesionServiceImpl implements ProfesionService {
    private final ProfesionMapper profesionMapper;
    private final ProfesionRepository profesionRepository;

    @Override
    public ProfesionResponseDTO guardarProfesion(ProfesionRequestDTO dto) {
        Profesion p=profesionMapper.DTOAEntidad(dto);
        Profesion p_insertada=profesionRepository.save(p);
        return profesionMapper.entidadADTO(p_insertada);
    }

    @Override
    public ProfesionResponseDTO actualizarProfesion(ProfesionRequestDTO dto, Long id) {
        Profesion p=profesionRepository.findById(id).orElseThrow(()->new RuntimeException("No existe dicha profesion"));
        profesionMapper.actualizarEntidadDesdeDTO(p,dto);
        Profesion p_actualizada=profesionRepository.save(p);
        return profesionMapper.entidadADTO(p_actualizada);
    }

    @Override
    public void eliminarProfesion(Long id) {

    }

    @Override
    public List<ProfesionResponseDTO> buscarTodos() {
        return List.of();
    }

    @Override
    public List<ProfesionResponseDTO> buscarNombre(String nombre) {
        return List.of();
    }

    @Override
    public boolean buscarExisteNombre(String nombre) {
        return false;
    }

    @Override
    public Long contarNombreRepetidos(String nombre) {
        return 0L;
    }
}
