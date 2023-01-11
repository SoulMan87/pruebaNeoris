package com.soulrebel.neoris.repository;

import com.soulrebel.neoris.entity.Cuenta;
import com.soulrebel.neoris.entity.Reporte;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {


    @Query(value = "SELECT new ReporteRespuesta(c.idCliente as id, c.numeroDeCuenta, c.tipoDeCuenta, c.saldoInicial, " +
            "SUM(CASE WHEN m.tipoMovimiento = 'DEBITO' THEN m.valor ELSE 0 END) as totalDebitos, " +
            "SUM(CASE WHEN m.tipoMovimiento = 'CREDITO' THEN m.valor ELSE 0 END) as totalCreditos, " +
            "(c.saldoInicial + SUM(CASE WHEN m.tipoMovimiento = 'CREDITO' THEN m.valor ELSE -m.valor END)) as saldoFinal, " +
            "p.nombre as nombre) " +
            "FROM Cuenta c " +
            "JOIN Cliente cl ON c.idCliente = cl.idCliente " +
            "JOIN Persona p ON cl.idCliente = p.idPersona " +
            "JOIN Movimiento m ON c.numeroDeCuenta = m.numeroDeCuenta " +
            "WHERE p.nombre = ?1 AND m.fecha BETWEEN ?2 AND ?3 " +
            "GROUP BY c.idCliente, c.numeroDeCuenta, c.tipoDeCuenta, c.saldoInicial",
            nativeQuery = true)
    List<Reporte> obtenerReportes(@Param("nombre") String nombre, @Param("fechaInical") String fechaInical,
                                  @Param("fechaFinal") String fechaFinal);
}
