package com.s1.GESTION_PROFESION.repository;

import com.s1.GESTION_PROFESION.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // AQUÍ VAN LAS "CONSULTAS SQL" utilizando las herramientas de SpringBoot como: FindById, FindAll, etc.
}
