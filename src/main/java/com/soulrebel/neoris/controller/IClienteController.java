package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.entity.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Validated
public interface IClienteController {

    @GetMapping("/clientes")
    ResponseEntity<List<Cliente>> obtenerTodosLosClientes();
}
