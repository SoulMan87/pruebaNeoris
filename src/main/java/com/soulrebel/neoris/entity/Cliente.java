package com.soulrebel.neoris.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Cliente extends Persona {
    private String contrasena;
    private boolean estado;

}
