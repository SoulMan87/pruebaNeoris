package com.soulrebel.neoris.service;

import com.soulrebel.neoris.entity.Movimiento;
import com.soulrebel.neoris.model.MovimientosRespuesta;

import java.util.List;
import java.util.Optional;


public interface MovimientoService {

    List<Movimiento> encontrarTodosLosMovimientos();

    Optional<Movimiento> crearMovimientos(Movimiento movimiento);

    Optional<Movimiento> actualizarMovimiento(long idMovimiento, Movimiento movimiento);

    void borrarMovimientoPorId(Long id);

    Optional<Movimiento> obtenerMovimientoPorId(long id);

    List<MovimientosRespuesta> encontrarMovimientosPorFechaUsuario();

    void hacerMovimiento(Long idCuenta, Movimiento movimiento);

}
