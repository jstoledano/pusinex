package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter @Entity
@Table(name = "app_localidad", uniqueConstraints={
        @UniqueConstraint( name = "idx_Seccion_Localidad",  columnNames ={"seccion_id","localidad"})
})
public class Localidad {
    @Id
    private Integer id;
    @Column(nullable = false, columnDefinition = "smallint")
    private int localidad;
    @Column(nullable = false, columnDefinition = "nvarchar", length = 100)
    private String nombre;
    @Column(nullable = false, columnDefinition = "nvarchar", length = 1)
    private String tipo;
    @OneToOne
    @JoinColumn(name="seccion_id", referencedColumnName = "seccion")
    private Seccion seccionId;

    @Override
    public String toString() {
        return String.format("%04d", seccionId.getSeccion()) + " " + String.format("%04d", localidad) + " " + nombre.toUpperCase();
    }
}
