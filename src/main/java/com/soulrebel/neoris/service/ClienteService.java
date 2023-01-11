package com.soulrebel.neoris.service;

import com.soulrebel.neoris.entity.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface ClienteService {
    List<Cliente> encontrarTodosLosClientes();

    Optional<Cliente> crearCliente(Cliente cliente);

    Optional<Cliente> actualizarCliente(long idCliente, Cliente cliente);

    Consumer<? super Cliente> borrarClientePorId(Long id);

    Optional<Cliente> obtenerClientePorId(long id);


}
