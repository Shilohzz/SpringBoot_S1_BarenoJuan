package com.s1.GESTION_PROFESION.repository;

import com.s1.GESTION_PROFESION.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
   // AQUÍ VAN LAS "CONSULTAS SQL" utilizando las herramientas de SpringBoot como: FindById, FindAll, etc.
}
