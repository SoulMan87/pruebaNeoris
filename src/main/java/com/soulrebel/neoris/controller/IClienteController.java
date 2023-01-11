package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.model.ClienteResponse;
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
@RequestMapping("/clientes")
public interface IClienteController {

    @GetMapping("/all")
    List<ClienteResponse> obtenerTodosLosClientes();

    @GetMapping("/{id}")
    ResponseEntity<ClienteResponse> obtenerClientePorId(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<ClienteResponse> actualizarCliente(@PathVariable Long id, @RequestBody ClienteResponse cliente);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> borrarClientePorId(@PathVariable Long id);

    @PostMapping
    ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteResponse cliente);
}
