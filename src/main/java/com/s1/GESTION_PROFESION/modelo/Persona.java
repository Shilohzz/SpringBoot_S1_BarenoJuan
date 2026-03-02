package com.s1.GESTION_PROFESION.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "persona")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String documento;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private Integer edad;
    @Column(nullable = false)
    private BigDecimal salario;
    //Agregacion, composición y asosiación.
    //Aqui definimos con el fectch el tipo de carga foranea.
    //Lazy = perezosa, Eager =  ansioso
    //Lazy= No carga la llave foranea ni sus datos en JPA.
    //Eager = CARGA TODAS LAS DEPENDENCIAS FORANEAS.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesion_id", nullable = false)
    private Profesion profesion;
}
