package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.model.MovimientoRespuesta;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Validated
public interface IMovimientoController {

    @GetMapping("/movimientos")
    ResponseEntity<List<MovimientoRespuesta>> obtenerTodosLosMovimientos();
}
