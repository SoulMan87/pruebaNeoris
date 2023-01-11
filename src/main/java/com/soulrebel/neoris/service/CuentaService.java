package com.soulrebel.neoris.service;

import com.soulrebel.neoris.entity.Cuenta;
import com.soulrebel.neoris.entity.Reporte;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface CuentaService {

    List<Cuenta> encontrarTodosLasCuentas();

    Optional<Cuenta> crearCuenta(Cuenta cuenta);

    Optional<Cuenta> actualizarCuenta(long idCuenta, Cuenta cuenta);

    Consumer<? super Cuenta> borrarCuentaPorId(Long id);

    Optional<Cuenta> obtenerCuentaPorId(long id);

    List<Reporte> obtenerCuentasRespotesDeCuentasPorFechas(String nombre, String fechaInicial, String fechaFinal);
}


