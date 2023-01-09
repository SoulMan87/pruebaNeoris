package com.soulrebel.neoris.exception;

import lombok.Getter;

@Getter
public class MovimientoException extends RuntimeException {

    private final String codigo;

    public MovimientoException(String codigo) {
        this.codigo = codigo;
    }
}
