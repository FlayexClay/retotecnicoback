package com.retotecnico.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
public class Futbolista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String caracteristicas;

    @ManyToOne
    @JoinColumn(name = "posicion_id")
    private Posicion posicion;
}

