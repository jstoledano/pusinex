package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames ={"entidad_entidad","municipio"})})
public class Municipio {
    @Id
    private Integer municipio;

    private String nombre;

    @OneToOne
    @JoinColumn()
    private Entidad entidad;

    @Override
    public  String toString() {
        return String.format("%02d", this.entidad.getEntidad()) +
                String.format("%03d", this.municipio) + " " +
                this.nombre.toUpperCase();
    }
}
