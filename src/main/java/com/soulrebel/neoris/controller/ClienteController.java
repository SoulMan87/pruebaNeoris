package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.entity.Cliente;
import com.soulrebel.neoris.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController implements IClienteController {

    private final ClienteService clienteService;

    @Override
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        return new ResponseEntity<List<Cliente>>(clienteService.encontrarTodosLosClientes(), HttpStatus.OK);
    }
}
