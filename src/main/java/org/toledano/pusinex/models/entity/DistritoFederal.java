package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints={@UniqueConstraint(columnNames ={"entidad","distritoFederal"})})
public class DistritoFederal {
    @Id
    private Integer distritoFederal;
    private String cabecera;

    @OneToOne
    @JoinColumn(name="entidad")
    private Entidad entidad;

    @Override
    public  String toString() {
        return String.format("%02d", this.entidad.getEntidad()) + String.format("%02d", this.distritoFederal) + " " + this.cabecera;
    }
}
