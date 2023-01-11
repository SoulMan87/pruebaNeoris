package com.soulrebel.neoris.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse extends PersonaResponse {

    private String contrasena;
    private Boolean estado;


}
