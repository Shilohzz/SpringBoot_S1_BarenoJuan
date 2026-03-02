package com.s1.GESTION_PROFESION.mapper;

import com.s1.GESTION_PROFESION.dto.Request.PersonaRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.PersonaResponseDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProfesionResponseDTO;
import com.s1.GESTION_PROFESION.modelo.Persona;
import com.s1.GESTION_PROFESION.modelo.Profesion;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    public PersonaResponseDTO entidadADTO(Persona persona, ProfesionResponseDTO dto){
        if(dto==null || persona==null) return null;
        return new PersonaResponseDTO(
                persona.getId(), persona.getDocumento(), persona.getNombre(),
                persona.getApellido(),
                persona.getEdad(),persona.getSalario(),dto
        );
    }
    public Persona DTOAEntidad(PersonaRequestDTO dto, Profesion profesion){
        if(dto==null || profesion==null) return null;
        Persona p=new Persona();
        p.setDocumento(dto.documento());
        p.setNombre(dto.nombre());
        p.setApellido(dto.apellido());
        p.setEdad(dto.edad());
        p.setSalario(dto.salario());
        p.setProfesion(profesion);
        return p;
    }

    public void actualizarEntidadDesdeDTO(Persona p,PersonaRequestDTO dto, Profesion profesion) {
        if (dto == null || profesion == null) return;
        p.setDocumento(dto.documento());
        p.setNombre(dto.nombre());
        p.setApellido(dto.apellido());
        p.setEdad(dto.edad());
        p.setSalario(dto.salario());
        p.setProfesion(profesion);
    }
}
