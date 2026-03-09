package com.s1.GESTION_PROFESION.service.impl;

import com.s1.GESTION_PROFESION.Exception.BusinessRuleException;
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
        Profesion p = profesionMapper.DTOAEntidad(dto);
        Profesion insertada = profesionRepository.save(p);
        return profesionMapper.entidadADTO(insertada);
    }

    @Override
    public ProfesionResponseDTO actualizarProfesion(ProfesionRequestDTO dto, Long id) {
        Profesion p = profesionRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Profesión con ID " + id + " no encontrada"));

        profesionMapper.actualizarEntidadDesdeDTO(p, dto);
        Profesion actualizada = profesionRepository.save(p);
        return profesionMapper.entidadADTO(actualizada);
    }

    @Override
    public void eliminarProfesion(Long id) {
        if (!profesionRepository.existsById(id)) {
            throw new BusinessRuleException("Profesión con ID " + id + " no encontrada");
        }
        // Si una persona tiene esta profesión asignada, la base de datos lanzará un error de integridad
        // que el GlobalExceptionHandler captura como error 500
        profesionRepository.deleteById(id);
    }

    @Override
    public List<ProfesionResponseDTO> buscarTodos() {
        return profesionRepository.findAll().stream()
                .map(profesionMapper::entidadADTO).toList();
    }

    @Override
    public List<ProfesionResponseDTO> buscarNombre(String nombre) {
        return profesionRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(profesionMapper::entidadADTO)
                .toList();
    }


}