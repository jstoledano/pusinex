package org.toledano.pusinex.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class User {
    @Id
    private String username;

    private String password;
}
