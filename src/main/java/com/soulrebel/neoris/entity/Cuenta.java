package com.soulrebel.neoris.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name = "CUENTA")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroDeCuenta;
    private String tipoDeCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

}
