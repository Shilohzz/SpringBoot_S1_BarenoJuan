package com.s1.GESTION_PROFESION.mapper;

import com.s1.GESTION_PROFESION.dto.Request.ProfesionRequestDTO;
import com.s1.GESTION_PROFESION.dto.Response.ProfesionResponseDTO;
import com.s1.GESTION_PROFESION.modelo.Profesion;
import org.springframework.stereotype.Component;

@Component
public class ProfesionMapper {
    public ProfesionResponseDTO entidadADTO(Profesion profesion){
        if(profesion==null) return null;
        return new ProfesionResponseDTO(
                profesion.getId(), profesion.getNombre(), profesion.getDescripcion()
        );
    }
    public Profesion DTOAEntidad(ProfesionRequestDTO dto){
        if(dto==null) return null;
        Profesion p=new Profesion();
        p.setDescripcion(dto.descripcion());
        p.setNombre(dto.nombre());
        return p;
    }

    public void actualizarEntidadDesdeDTO(Profesion profesion, ProfesionRequestDTO dto){
        if(profesion==null || dto==null) return;
        profesion.setDescripcion(dto.descripcion());
        profesion.setNombre(dto.nombre());
    }

}
