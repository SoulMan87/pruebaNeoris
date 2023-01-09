package com.soulrebel.neoris.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Table(name = "CLIENTE")
@Entity
@PrimaryKeyJoinColumn(name = "clienteId")
@Setter
@Getter
@NoArgsConstructor
@Builder
public class Cliente extends Persona {
    private String contrasena;
    private boolean estado;

}
