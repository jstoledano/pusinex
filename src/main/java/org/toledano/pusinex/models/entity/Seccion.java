package org.toledano.pusinex.models.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "app_seccion", uniqueConstraints={
        @UniqueConstraint( name = "idx_Entidad_Seccion",  columnNames ={"entidad_id","seccion"})
})
public class Seccion {
    @Id
    private int seccion;

    @Column(name = "tipo", nullable = false, columnDefinition = "nvarchar", length = 1)
    private String tipo;

    @OneToOne
    @JoinColumn(name="distrito_federal_id", referencedColumnName = "distrito_federal")
    private DistritoFederal distritoFederalId;

    @OneToOne
    @JoinColumn(name="entidad_id", referencedColumnName = "entidad")
    private Entidad entidadId;

    @OneToOne
    @JoinColumn(name="municipio_id", referencedColumnName = "municipio")
    private Municipio municipioId;

    @Override
    public  String toString() {
        return String.format("%02d", this.entidadId.getEntidad()) +
                String.format("%02d", this.distritoFederalId.getDistritoFederal()) +
                String.format("%03d", this.municipioId.getMunicipio()) +
                " - " +
                String.format("%04d", this.seccion);
    }
}
