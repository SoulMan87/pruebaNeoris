package com.soulrebel.neoris.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteRespuesta {

    private Long id;
    private String numeroDeCuenta;
    private String tipoDeCuenta;
    private Long saldoInicial;
    private Long totalDebitos;
    private Long totalCreditos;
    private Long saldoFinal;
}
