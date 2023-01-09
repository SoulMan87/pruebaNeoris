package com.soulrebel.neoris.exception;

import lombok.Getter;

@Getter
public class CuentaException extends RuntimeException {

    private final String codigo;

    public CuentaException(String codigo) {
        this.codigo = codigo;
    }
}
