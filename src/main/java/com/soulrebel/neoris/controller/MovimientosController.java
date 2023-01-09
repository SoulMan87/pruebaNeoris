package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.model.MovimientoRespuesta;
import com.soulrebel.neoris.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovimientosController implements IMovimientoController {

    private final MovimientoService movimientoService;


    @Override
    public ResponseEntity<List<MovimientoRespuesta>> obtenerTodosLosMovimientos() {

        return null;
    }
}
