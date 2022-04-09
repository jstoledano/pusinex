package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="app_municipio", uniqueConstraints={
        @UniqueConstraint( name = "idx_Entidad_Municipio",  columnNames ={"entidad_id","municipio"})
})
public class Municipio {
    @Id
    private Integer municipio;

    @Column(name = "nombre", nullable = false, columnDefinition = "nvarchar", length = 100)
    private String nombre;

    @OneToOne
    @JoinColumn(name="entidad_id", referencedColumnName = "entidad")
    private Entidad entidadId;

    @Override
    public  String toString() {
        return String.format("%02d", this.entidadId.getEntidad()) +
                String.format("%03d", this.municipio) + " " +
                this.nombre.toUpperCase();
    }
}
