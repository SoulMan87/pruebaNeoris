package com.soulrebel.neoris.service.impl;

import com.soulrebel.neoris.entity.Cuenta;
import com.soulrebel.neoris.entity.Movimiento;
import com.soulrebel.neoris.exception.MovimientoException;
import com.soulrebel.neoris.model.MovimientoRespuesta;
import com.soulrebel.neoris.repository.MovimientoRepository;
import com.soulrebel.neoris.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.soulrebel.neoris.exception.Constantes.MOVIMIENTO_NO_ENCONTRADO;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository repository;


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

        Movimiento movimiento = repository.findById(id)
                .orElseThrow(() -> new MovimientoException(MOVIMIENTO_NO_ENCONTRADO));

        repository.delete(movimiento);

    }

    @Override
    public Optional<Movimiento> obtenerMovimientoPorId(long id) {
        var movimiento = repository.findById(id);
        if (movimiento.isEmpty()) {
            throw new MovimientoException(MOVIMIENTO_NO_ENCONTRADO);
        } else {
            return movimiento;
        }

    }

    @Override
    public List<MovimientoRespuesta> encontrarMovimientosPorFechaUsuario() {
        return repository.encontrarMovimientosPorFechaUsuario();
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
