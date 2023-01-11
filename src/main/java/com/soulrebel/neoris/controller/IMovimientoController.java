package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.model.MovimientoRespuesta;
import com.soulrebel.neoris.model.MovimientosRespuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequestMapping("/movimientos")
public interface IMovimientoController {

    @GetMapping("/all")
    List<MovimientoRespuesta> obtenerTodosLosMovimiento();

    @GetMapping("/{id}")
    ResponseEntity<MovimientoRespuesta> obtenerMovimientoPorId(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<MovimientoRespuesta> actualizarMovimiento(@PathVariable Long id,
                                                              @RequestBody MovimientoRespuesta movimientosRepuesta);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> borrarMovimientoPorId(@PathVariable Long id);

    @PostMapping
    ResponseEntity<MovimientoRespuesta> crearMoviminto(@RequestBody MovimientoRespuesta movimientoRespuesta);

    @PostMapping("/movimientos/hacer/{idCuenta}")
    @ResponseStatus(HttpStatus.CREATED)
    void hacerMovimiento(@PathVariable Long idCuenta, @Valid @RequestBody MovimientosRespuesta movimientosRespuesta);
}

