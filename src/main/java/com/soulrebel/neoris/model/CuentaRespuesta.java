package com.soulrebel.neoris.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaRespuesta {

    private Long id;
    private String numeroDeCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private ClienteResponse clienteResponse;
}
