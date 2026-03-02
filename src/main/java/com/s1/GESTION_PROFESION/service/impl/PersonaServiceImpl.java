package com.s1.GESTION_PROFESION.service.impl;

import com.s1.GESTION_PROFESION.dto.Request.PersonaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.PersonaResponseDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProfesionResponseDTO;
import com.s1.GESTION_PROFESION.mapper.PersonaMapper;
import com.s1.GESTION_PROFESION.mapper.ProfesionMapper;
import com.s1.GESTION_PROFESION.modelo.Persona;
import com.s1.GESTION_PROFESION.modelo.Profesion;
import com.s1.GESTION_PROFESION.repository.PersonaRepository;
import com.s1.GESTION_PROFESION.repository.ProfesionRepository;
import com.s1.GESTION_PROFESION.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final ProfesionRepository profesionRepository;
    private final PersonaMapper personaMapper;
    private final ProfesionMapper profesionMapper;

    @Override
    @Transactional
    public PersonaResponseDTO guardarPersona(PersonaRequestDTO dto) {
        // Validar que la profesión exista
        Profesion profesion = profesionRepository.findById(dto.profesionId())
                .orElseThrow(() -> new RuntimeException("Error: La profesión con ID " + dto.profesionId() + " no existe."));

        // Convertir DTO a Entidad
        Persona persona = personaMapper.DTOAEntidad(dto, profesion);

        // Guardar
        Persona personaGuardada = personaRepository.save(persona);

        // Retornar Respuesta mapeada
        ProfesionResponseDTO profResp = profesionMapper.entidadADTO(personaGuardada.getProfesion());
        return personaMapper.entidadADTO(personaGuardada, profResp);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponseDTO buscarPorId(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        ProfesionResponseDTO profResp = profesionMapper.entidadADTO(persona.getProfesion());
        return personaMapper.entidadADTO(persona, profResp);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> buscarTodos() {
        return personaRepository.findAll().stream()
                .map(p -> personaMapper.entidadADTO(p, profesionMapper.entidadADTO(p.getProfesion())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PersonaResponseDTO actualizarPersona(PersonaRequestDTO dto, Long id) {
        Persona personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Profesion profesion = profesionRepository.findById(dto.profesionId())
                .orElseThrow(() -> new RuntimeException("Profesión no encontrada"));

        personaMapper.actualizarEntidadDesdeDTO(personaExistente, dto, profesion);
        Persona actualizada = personaRepository.save(personaExistente);

        return personaMapper.entidadADTO(actualizada, profesionMapper.entidadADTO(actualizada.getProfesion()));
    }

    @Override
    @Transactional
    public void eliminarPersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Persona no existe");
        }
        personaRepository.deleteById(id);
    }
}