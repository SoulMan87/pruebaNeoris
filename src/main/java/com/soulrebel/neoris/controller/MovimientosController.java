package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.entity.Movimiento;
import com.soulrebel.neoris.model.MovimientoRespuesta;
import com.soulrebel.neoris.model.MovimientosRespuesta;
import com.soulrebel.neoris.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovimientosController implements IMovimientoController {

    private final MovimientoService movimientoService;

    private final ModelMapper modelMapper;


    @Override
    public List<MovimientoRespuesta> obtenerTodosLosMovimiento() {
        return movimientoService.encontrarTodosLosMovimientos()
                .stream().map(movimiento -> modelMapper.map(movimiento, MovimientoRespuesta.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<MovimientoRespuesta> obtenerMovimientoPorId(Long id) {
        Optional<Movimiento> movimiento = movimientoService.obtenerMovimientoPorId(id);

        MovimientoRespuesta movimientoRespuesta = modelMapper.map(movimiento, MovimientoRespuesta.class);

        return ResponseEntity.ok().body(movimientoRespuesta);
    }

    @Override
    public ResponseEntity<MovimientoRespuesta> actualizarMovimiento(Long id, MovimientoRespuesta movimientoRespuesta) {
        Movimiento movimientoRequest = modelMapper.map(movimientoRespuesta, Movimiento.class);

        Optional<Movimiento> actualizarMoviento = movimientoService.actualizarMovimiento(id, movimientoRequest);

        MovimientoRespuesta movimiento = modelMapper.map(actualizarMoviento, MovimientoRespuesta.class);
        return ResponseEntity.ok().body(movimiento);
    }

    @Override
    public ResponseEntity<Void> borrarMovimientoPorId(Long id) {
        movimientoService.obtenerMovimientoPorId(id).ifPresent(movimientoService.borrarMovimientoPorId(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MovimientoRespuesta> crearMoviminto(MovimientoRespuesta movimientoRespuesta) {
        Movimiento movimientoRequest = modelMapper.map(movimientoRespuesta, Movimiento.class);

        Optional<Movimiento> crearMovimiento = movimientoService.crearMovimientos(movimientoRequest);

        MovimientoRespuesta movimiento = modelMapper.map(crearMovimiento, MovimientoRespuesta.class);

        return new ResponseEntity<>(movimiento, HttpStatus.CREATED);
    }

    @Override
    public void hacerMovimiento(Long id, MovimientosRespuesta movimientosRespuesta) {
        Movimiento movimiento = modelMapper.map(movimientosRespuesta, Movimiento.class);
        movimientoService.hacerMovimiento(id, movimiento);
    }
}
