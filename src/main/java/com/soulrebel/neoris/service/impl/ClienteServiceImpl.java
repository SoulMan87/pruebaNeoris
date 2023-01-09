package com.soulrebel.neoris.service.impl;

import com.soulrebel.neoris.entity.Cliente;
import com.soulrebel.neoris.repository.ClienteRepository;
import com.soulrebel.neoris.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.soulrebel.neoris.exception.Constantes.CLIENTE_NO_ENCONTRADO;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;


    @Override
    public List<Cliente> encontrarTodosLosClientes() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    public Optional<Cliente> crearCliente(Cliente cliente) {
        cliente.setEstado(true);
        return Optional.of(repository.save(cliente));
    }


    @Override
    public Optional<Cliente> actualizarCliente(long idCliente, Cliente clienteRequest) {
        final Optional<Cliente> cliente = repository.findById(idCliente);
        if (cliente.isPresent()) {
            Cliente clienteActulizar = cliente.get();
            clienteActulizar.setNombre(clienteRequest.getNombre());
            clienteActulizar.setGenero(clienteRequest.getGenero());
            clienteActulizar.setEdad(clienteRequest.getEdad());
            clienteActulizar.setIdentificacion(clienteRequest.getIdentificacion());
            clienteActulizar.setDireccion(clienteRequest.getDireccion());
            clienteActulizar.setTelefono(clienteActulizar.getTelefono());
            clienteActulizar.setContrasena(clienteRequest.getContrasena());
            clienteActulizar.setEstado(clienteRequest.isEstado());
            return Optional.of(repository.save(clienteActulizar));
        } else {
            throw new EntityNotFoundException(CLIENTE_NO_ENCONTRADO);
        }
    }

    @Override
    public void borrarClientePorId(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            repository.delete(cliente.get());
        } else {
            throw new EntityNotFoundException(CLIENTE_NO_ENCONTRADO);
        }

    }

    @Override
    public Optional<Cliente> obtenerClientePorId(long id) {
        Optional<Cliente> cliente = repository.findById(id);
        return Optional.ofNullable(cliente.orElseThrow(() -> new EntityNotFoundException(CLIENTE_NO_ENCONTRADO)));
    }


}
