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

    @OneToOne @JoinColumn(name="id")
    private Localidad localidadId;

    @OneToOne @JoinColumn(name="status")
    private StatusPusinex statusPusinex;
}
