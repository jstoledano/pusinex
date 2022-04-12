package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Entidad {
    @Id
    private int entidad;
    private String nombre;

    @Override
    public String toString() {
        return entidad + " " + nombre;
    }
}
