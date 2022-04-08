package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="app_distritofederal")
public class DistritoFederal {
    @Id
    @Column(name="distrito_federal", nullable=false)
    private Integer distrito_federal;

    @Column(name = "cabecera", nullable = false, columnDefinition = "nvarchar", length = 100)
    private String cabecera;

    @OneToOne
    @JoinColumn(name="entidad_id", referencedColumnName = "entidad")
    private Entidad entidad_id;
}
