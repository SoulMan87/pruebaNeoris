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

    private Long id;
    private LocalDate fecha;
    private String tipoDeMovimiento;
    private Double cantidad;
    private BigDecimal saldo;
    private Long idCuenta;

}
