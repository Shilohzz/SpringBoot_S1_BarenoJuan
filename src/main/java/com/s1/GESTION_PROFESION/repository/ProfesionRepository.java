package com.s1.GESTION_PROFESION.repository;

import com.s1.GESTION_PROFESION.modelo.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesionRepository extends JpaRepository<Profesion,Long> {
    // AQUÍ VAN LAS "CONSULTAS SQL" utilizando las herramientas de SpringBoot como: FindById, FindAll, etc.String nombre)
}
