package com.soulrebel.neoris.service.impl;

import com.soulrebel.neoris.entity.Cliente;
import com.soulrebel.neoris.repository.ClienteRepository;
import com.soulrebel.neoris.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public List<Cliente> encontrarTodosLosClientes() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    public Optional<Cliente> encontrarClientePorId(Long id) {
        return Optional.of(repository.findById(id).orElseThrow());
    }

    @Override
    public Optional<Cliente> guardarCliente(Cliente cliente) {
        return Optional.of(repository.save(cliente));
    }

    @Override
    public void borrarClientePorId(Long id) {
        repository.deleteById(id);
    }
}
