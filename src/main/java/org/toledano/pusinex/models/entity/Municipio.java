package org.toledano.pusinex.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MUNICIPIO")
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE )
    private Long municipio;
    private String nombre;

    public Long getMunicipio() {
        return municipio;
    }
    public void setMunicipio(Long municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre() {
        setNombre();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return municipio + " " + nombre;
    }
}
