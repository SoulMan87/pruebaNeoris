package com.soulrebel.neoris.service;

import com.soulrebel.neoris.entity.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    List<Cuenta> encontrarTodosLasCuentas();

    Optional<Cuenta> crearCuenta(Cuenta cuenta);

    Optional<Cuenta> actualizarCuenta(long idCuenta, Cuenta cuenta);

    void borrarCuentaPorId(Long id);

    Optional<Cuenta> obtenerCuentaPorId(long id);
}


