package com.soulrebel.neoris.service.impl;

import com.soulrebel.neoris.entity.Cuenta;
import com.soulrebel.neoris.exception.CuentaException;
import com.soulrebel.neoris.repository.CuentaRepository;
import com.soulrebel.neoris.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.soulrebel.neoris.exception.Constantes.CUENTA_NO_ENCONTRADA;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository repository;

    @Override
    public List<Cuenta> encontrarTodosLasCuentas() {
        return (List<Cuenta>) repository.findAll();
    }

    @Override
    public Optional<Cuenta> crearCuenta(Cuenta cuenta) {
        cuenta.setEstado(true);
        return Optional.of(repository.save(cuenta));
    }

    @Override
    public Optional<Cuenta> actualizarCuenta(long idCuenta, Cuenta cuentaRequest) {

        Optional<Cuenta> cuenta = repository.findById(idCuenta);
        if (cuenta.isPresent()) {
            return Optional.of(repository.save(buildCuenta(cuentaRequest)));
        } else {
            throw new CuentaException(CUENTA_NO_ENCONTRADA);
        }
    }

    @Override
    public void borrarCuentaPorId(Long id) {
        Optional<Cuenta> cuenta = repository.findById(id);

        if (cuenta.isPresent()) {
            repository.delete(cuenta.get());
        } else {
            throw new CuentaException(CUENTA_NO_ENCONTRADA);
        }

    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorId(long id) {
        Optional<Cuenta> cuenta = repository.findById(id);
        return Optional.ofNullable(cuenta.orElseThrow(() -> new CuentaException(CUENTA_NO_ENCONTRADA)));
    }

    private Cuenta buildCuenta(Cuenta cuenta) {
        return Cuenta.builder()
                .numeroDeCuenta(cuenta.getNumeroDeCuenta())
                .tipoDeCuenta(cuenta.getTipoDeCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .build();
    }
}
