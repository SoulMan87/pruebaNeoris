package com.soulrebel.neoris.repository;

import com.soulrebel.neoris.entity.Movimiento;
import com.soulrebel.neoris.model.MovimientosRespuesta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    @Query(value = "SELECT NEW MovimientoRespuesta(movimiento.fecha, persona.nombre" +
            ", cliente.numeroDeCuenta, cliente.tipoDeCuenta, cuenta.saldoInicial, cuenta.estado, " +
            "       movimiento.tipoDeMovimiento, movimiento.saldoDisponible) " +
            "FROM Movimiento movimiento " +
            "JOIN movimiento.cuenta cuenta " +
            "JOIN cuenta.cliente cliente " +
            "JOIN cliente.idPesona persona " +
            "ORDER BY movimiento.fecha", nativeQuery = true)
    List<MovimientosRespuesta> encontrarMovimientosPorFechaUsuario();
}

