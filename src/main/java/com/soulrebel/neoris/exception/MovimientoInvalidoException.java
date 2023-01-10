package com.soulrebel.neoris.exception;

import lombok.Getter;

@Getter
public class MovimientoInvalidoException extends RuntimeException {

    private final String codigo;

    public MovimientoInvalidoException(String codigo) {
        this.codigo = codigo;
    }
}
