package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class StatusPusinex {
    @Id @Column(length = 1)
    private String id;

    private String descripcion;
}
