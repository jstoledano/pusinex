package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Setter @Getter
@Table(name = "pusinexj")
public class Pusinex {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne @JoinColumn()
    private Localidad localidad;

    @OneToOne @JoinColumn(name="status")
    private StatusPusinex statusPusinex;

    private java.sql.Date fechaLevantamiento;

    @Override
    public String toString() {
        return String.format("%04d", this.localidad.getSeccionId().getSeccion()) + " " +
                String.format("%04d", this.localidad.getLocalidad()) + " " +
                this.localidad.getNombre()
                + " (" + this.getFechaLevantamiento() + ")";
    }
}
