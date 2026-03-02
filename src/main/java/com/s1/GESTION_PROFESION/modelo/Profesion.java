package com.s1.GESTION_PROFESION.modelo;

import jakarta.persistence.*;
import lombok.*;

/**
 * PRIMERO:
 * Se necesita Mappear la base de datos, para que, JPA identifique cuales son
 * las entidades que va a manipular.
 */

@Entity
@Table(name = "profesion")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Profesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;
}