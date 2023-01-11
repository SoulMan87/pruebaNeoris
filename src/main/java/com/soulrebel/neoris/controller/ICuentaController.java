package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.model.CuentaRespuesta;
import com.soulrebel.neoris.model.ReporteRespuesta;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@RequestMapping("/cuentas")
public interface ICuentaController {

    @GetMapping("/all")
    List<CuentaRespuesta> obtenerTodosLasCuentas();

    @GetMapping("/{id}")
    ResponseEntity<CuentaRespuesta> obtenerCuentaPorId(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<CuentaRespuesta> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaRespuesta cuenta);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> borrarCuentaPorId(@PathVariable Long id);

    @PostMapping
    ResponseEntity<CuentaRespuesta> crearCuenta(@RequestBody CuentaRespuesta cuenta);

    @GetMapping("/reportes/{nombre}/{fechaInicial}/{fechaFinal}")
    ResponseEntity<List<ReporteRespuesta>> obtenerCuentasRespotesDeCuentasPorFechas(@PathVariable String nombre,
                                                                           @PathVariable String fechaFinal,
                                                                           @PathVariable String fechaInicial);
}
