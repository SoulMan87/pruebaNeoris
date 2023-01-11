package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.entity.Cliente;
import com.soulrebel.neoris.model.ClienteResponse;
import com.soulrebel.neoris.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController implements IClienteController {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Override
    public List<ClienteResponse> obtenerTodosLosClientes() {
        return clienteService.encontrarTodosLosClientes()
                .stream().map(cliente -> modelMapper.map(cliente, ClienteResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ClienteResponse> obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);

        ClienteResponse clienteResponse = modelMapper.map(cliente, ClienteResponse.class);

        return ResponseEntity.ok().body(clienteResponse);
    }

    @Override
    public ResponseEntity<ClienteResponse> actualizarCliente(Long id, ClienteResponse cliente) {
        Cliente clienteRequest = modelMapper.map(cliente, Cliente.class);

        Optional<Cliente> actualizarCliente = clienteService.actualizarCliente(id, clienteRequest);

        ClienteResponse clienteResponse = modelMapper.map(actualizarCliente, ClienteResponse.class);
        return ResponseEntity.ok().body(clienteResponse);
    }

    @Override
    public ResponseEntity<Void> borrarClientePorId(Long id) {
        clienteService.obtenerClientePorId(id).ifPresent(clienteService.borrarClientePorId(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ClienteResponse> crearCliente(ClienteResponse cliente) {
        Cliente clienteRequest = modelMapper.map(cliente, Cliente.class);

        Optional<Cliente> crearCliente = clienteService.crearCliente(clienteRequest);

        ClienteResponse clienteResponse = modelMapper.map(crearCliente, ClienteResponse.class);

        return new ResponseEntity<>(clienteResponse, HttpStatus.CREATED);
    }

}
