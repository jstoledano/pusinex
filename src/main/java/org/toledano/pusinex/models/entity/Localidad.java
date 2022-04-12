package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter @Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames ={"seccion","localidad"})})
public class Localidad {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private int localidad;

    private String nombre;

    @Column(length = 1)
    private String tipo;

    @OneToOne
    @JoinColumn(name = "seccion")
    private Seccion seccion;

    @Override
    public String toString() {
        return String.format(
                "%04d", seccion.getSeccion()) +
                " " + String.format("%04d", localidad) +
                " " + nombre.toUpperCase();
    }
}
