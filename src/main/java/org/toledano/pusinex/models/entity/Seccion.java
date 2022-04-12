package org.toledano.pusinex.models.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames ={"entidad_entidad","seccion"})})
public class Seccion {
    @Id
    private int seccion;

    @Column(length = 1)
    private String tipo;

    @OneToOne
    @JoinColumn()
    private DistritoFederal distritoFederal;

    @OneToOne
    @JoinColumn()
    private Entidad entidad;

    @OneToOne
    @JoinColumn()
    private Municipio municipio;

    @Override
    public  String toString() {
        return String.format("%02d", this.entidad.getEntidad()) +
                String.format("%02d", this.distritoFederal.getDistritoFederal()) +
                String.format("%03d", this.municipio.getMunicipio()) +
                " - " +
                String.format("%04d", this.seccion);
    }
}
