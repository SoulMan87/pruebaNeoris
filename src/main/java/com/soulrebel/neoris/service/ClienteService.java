package com.soulrebel.neoris.service;

import com.soulrebel.neoris.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> encontrarTodosLosClientes();

    Optional<Cliente> crearCliente(Cliente cliente);

    Optional<Cliente> actualizarCliente(long idCliente, Cliente cliente);

    void borrarClientePorId(Long id);

    Optional<Cliente> obtenerClientePorId(long id);


}
