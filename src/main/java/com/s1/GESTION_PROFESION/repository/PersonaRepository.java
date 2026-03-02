package com.s1.GESTION_PROFESION.repository;

import com.s1.GESTION_PROFESION.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    //select p.* from persona p left join profesion pr on p.profesion_id=pr.id
    //where pr.nombre="sistemas";
    //List<Persona> findByProfesionNombreAndProfesionDescripcion(String profesion);
    //select dv.* from detalle_venta dv left join producto p on dv.producto_id=p.id
    //left join marca m on p.marca_id=m.marca where m.nombre="samsung";
    //List<Persona> findByProductoMarcaNombre(String nombre);
}
