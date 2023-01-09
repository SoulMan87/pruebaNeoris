package com.soulrebel.neoris.service.impl;

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

        final var movimiento = repository.findById(idMovimiento)
                .orElseThrow(() -> new MovimientoException("Movimiento" + idMovimiento));
        movimiento.setIdMovimiento(movimientoRequest.getIdMovimiento());
        movimiento.setTipoMovimiento(movimientoRequest.getTipoMovimiento());
        movimiento.setFecha(movimientoRequest.getFecha());
        movimiento.setValor(movimientoRequest.getValor());
        movimiento.setSaldo(movimientoRequest.getSaldo());

        return Optional.of(repository.save(movimiento));
    }

    @Override
    public void borrarMovimientoPorId(Long id) {

        Movimiento movimiento = repository.findById(id)
                .orElseThrow(() -> new MovimientoException("Movimiento no existe"));

        repository.delete(movimiento);

    }

    @Override
    public Optional<Movimiento> optenerMovimientoPorId(long id) {
        var movimiento = repository.findById(id);
        if (movimiento.isEmpty()) {
            throw new MovimientoException("No se encontró movimiento");
        } else {
            return movimiento;
        }

    }

    @Override
    public List<MovimientoRespuesta> encontrarMovimientosPorFechaUsuario() {
        return repository.encontrarMovimientosPorFechaUsuario();
    }


}
