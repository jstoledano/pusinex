package org.toledano.pusinex.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "app_statuspusinex")
public class StatusPusinex {
    @Id @Column(nullable = false,columnDefinition = "nvarchar", length = 1)
    private String status;

    @Column(nullable = false, columnDefinition = "nvarchar", length = 100)
    private String descripcion;
}
