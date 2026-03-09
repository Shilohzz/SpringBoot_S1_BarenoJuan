package com.s1.GESTION_PROFESION.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private BigDecimal precio;

    private Integer stock;
}