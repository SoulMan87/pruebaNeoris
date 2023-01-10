package com.soulrebel.neoris.exception;

import lombok.Getter;

@Getter
public class MovimientoInsuficienteExcepetion extends RuntimeException {

    private final String codigo;

    public MovimientoInsuficienteExcepetion(String codigo) {
        this.codigo = codigo;

    }
}
