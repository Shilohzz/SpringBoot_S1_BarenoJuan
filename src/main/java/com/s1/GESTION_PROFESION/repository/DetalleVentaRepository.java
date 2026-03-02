package com.s1.GESTION_PROFESION.repository;

import com.s1.GESTION_PROFESION.modelo.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {}
