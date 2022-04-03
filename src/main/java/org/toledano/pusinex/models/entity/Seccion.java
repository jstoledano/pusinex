package org.toledano.pusinex.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "seccion")
public class Seccion  implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seccion", nullable = false)
    private Long seccion;

    @Column(name = "distrito", nullable = false)
    private Long distrito;

    @Column(name="distrito_local", nullable = false)
    private Long distrito_local;

    @Column(name = "municipio", nullable = false)
    private Long municipio;

    @ManyToOne
    @JoinColumn(name="municipio", insertable=false, updatable=false)
    private Municipio nom_municipio;

    public Municipio getNom_municipio() {
        return nom_municipio;
    }

    public Long getSeccion() {
        return seccion;
    }

    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }

    public Long getDistrito() {
        return distrito;
    }

    public void setDistrito(Long distrito) {
        this.distrito = distrito;
    }

    public Long getDistrito_local() {
        return distrito_local;
    }

    public void setDistrito_local(Long distrito_local) {
        this.distrito_local = distrito_local;
    }

    public Long getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Long municipio) {
        this.municipio = municipio;
    }

    public String toString() {
        return "0" + this.getSeccion();
    }
}
