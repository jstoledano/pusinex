package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "app_entidad")
public class Entidad {
    @Id
    @Column(name = "entidad", nullable = false)
    private int entidad;

    @Column(name = "nombre", nullable = false, columnDefinition = "nvarchar", length = 100)
    private String nombre;

    @Override
    public String toString() {
        return entidad + " " + nombre;
    }
}
