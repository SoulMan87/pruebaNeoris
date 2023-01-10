package com.soulrebel.neoris.service.impl;

import com.soulrebel.neoris.entity.Cuenta;
import com.soulrebel.neoris.entity.Movimiento;
import com.soulrebel.neoris.exception.MovimientoException;
import com.soulrebel.neoris.exception.MovimientoInsuficienteExcepetion;
import com.soulrebel.neoris.exception.MovimientoInvalidoException;
import com.soulrebel.neoris.model.MovimientosRespuesta;
import com.soulrebel.neoris.repository.CuentaRepository;
import com.soulrebel.neoris.repository.MovimientoRepository;
import com.soulrebel.neoris.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.soulrebel.neoris.exception.Constantes.CERO;
import static com.soulrebel.neoris.exception.Constantes.CREDITO;
import static com.soulrebel.neoris.exception.Constantes.DEBITO;
import static com.soulrebel.neoris.exception.Constantes.MOVIMIENTO_INVALIDO;
import static com.soulrebel.neoris.exception.Constantes.MOVIMIENTO_NO_ENCONTRADO;
import static com.soulrebel.neoris.exception.Constantes.SALDO_INVALIDO;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository repository;

    private final CuentaRepository cuentaRepository;


    @Override
    public List<Movimiento> encontrarTodosLosMovimientos() {
        return (List<Movimiento>) repository.findAll();
    }

    @Override
    public Optional<Movimiento> crearMovimientos(Movimiento movimiento) {
        return Optional.of(repository.save(movimiento));
    }

    @Override
    public Optional<Movimiento> actualizarMovimiento(long idMovimiento, Movimiento movimientoRequest) {

        final Optional<Movimiento> movimiento = repository.findById(idMovimiento);
        if (movimiento.isPresent()) {
            return Optional.of(repository.save(buildMovimiento(movimientoRequest)));
        } else {
            throw new MovimientoException(MOVIMIENTO_NO_ENCONTRADO);
        }

    }

    @Override
    public void borrarMovimientoPorId(Long id) {

        final Movimiento movimiento = repository.findById(id)
                .orElseThrow(() -> new MovimientoException(MOVIMIENTO_NO_ENCONTRADO));

        repository.delete(movimiento);

    }

    @Override
    public Optional<Movimiento> obtenerMovimientoPorId(long id) {
       final var movimiento = repository.findById(id);
        if (movimiento.isEmpty()) {
            throw new MovimientoException(MOVIMIENTO_NO_ENCONTRADO);
        } else {
            return movimiento;
        }

    }

    @Override
    public List<MovimientosRespuesta> encontrarMovimientosPorFechaUsuario() {
        return repository.encontrarMovimientosPorFechaUsuario();
    }

    @Override
    public void hacerMovimiento(Long idCuenta, Movimiento movimiento) {

        cuentaRepository.findById(idCuenta)
                .filter(cuenta -> movimiento.getTipoMovimiento().equals(CREDITO) &&
                        movimiento.getValor().compareTo(BigDecimal.ZERO) > CERO ||
                        movimiento.getTipoMovimiento().equals("debito") &&
                                movimiento.getValor().compareTo(BigDecimal.ZERO) < CERO).ifPresentOrElse(
                        cuenta -> {
                            if (movimiento.getTipoMovimiento().equals(DEBITO)) {
                                checkSaldo(cuenta, movimiento.getValor().doubleValue());
                            }
                            actualizarCuentaSaldo(cuenta, movimiento.getValor().doubleValue());
                        }, () -> {
                            throw new MovimientoInvalidoException(MOVIMIENTO_INVALIDO);
                        }
                );

    }

    private void actualizarCuentaSaldo(Cuenta cuenta, double cantidad) {
        cuenta.setSaldoInicial(cuenta.getSaldoInicial().add(BigDecimal.valueOf(cantidad)));
        cuentaRepository.save(cuenta);
    }

    private void checkSaldo(Cuenta cuenta, double cantidad) {
        if (cuenta.getSaldoInicial().add(BigDecimal.valueOf(cantidad)).compareTo(BigDecimal.ZERO) < CERO) {
            throw new MovimientoInsuficienteExcepetion(SALDO_INVALIDO);
        }
    }

    private Movimiento buildMovimiento(Movimiento movimiento) {
        Cuenta cuenta = new Cuenta();
        return Movimiento.builder()
                .fecha(movimiento.getFecha())
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .saldo(movimiento.getSaldo())
                .valor(movimiento.getValor())
                .cuenta(cuenta)
                .build();
    }

}
