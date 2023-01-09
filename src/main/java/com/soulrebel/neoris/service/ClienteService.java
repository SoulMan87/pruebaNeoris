package com.soulrebel.neoris.service;

import com.soulrebel.neoris.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> encontrarTodosLosClientes();

    Optional<Cliente> encontrarClientePorId(Long id);

    Optional<Cliente> guardarCliente(Cliente cliente);

    void borrarClientePorId(Long id);
}
