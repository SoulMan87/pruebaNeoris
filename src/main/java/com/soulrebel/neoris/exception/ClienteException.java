package com.soulrebel.neoris.exception;

import lombok.Getter;

@Getter
public class ClienteException extends RuntimeException {

    private final String codigo;

    public ClienteException(String codigo) {
        this.codigo = codigo;
    }
}
