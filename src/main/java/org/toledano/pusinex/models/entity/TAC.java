package org.toledano.pusinex.models.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TAC {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String nombre;
    private String apellidos;

    @OneToOne
    private DistritoFederal distritoFederal;
}
