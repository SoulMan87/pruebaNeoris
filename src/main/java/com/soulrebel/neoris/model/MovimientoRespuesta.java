package com.soulrebel.neoris.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoRespuesta {

    private LocalDate fecha;
    private String nombre;
    private String numeroDeCuenta;
    private String tipoDeCuenta;
    private BigDecimal saldoInicial;
    private boolean estado;
    private String tipoDeMovimiento;
    private BigDecimal saldo;
}
