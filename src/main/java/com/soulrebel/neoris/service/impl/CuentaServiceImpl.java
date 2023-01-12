package com.soulrebel.neoris.service.impl;

import com.soulrebel.neoris.entity.Cuenta;
import com.soulrebel.neoris.entity.Movimiento;
import com.soulrebel.neoris.entity.Reporte;
import com.soulrebel.neoris.exception.CuentaException;
import com.soulrebel.neoris.model.TipoMovimiento;
import com.soulrebel.neoris.repository.CuentaRepository;
import com.soulrebel.neoris.repository.ReporteRepository;
import com.soulrebel.neoris.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.soulrebel.neoris.exception.Constantes.CUENTA_NO_ENCONTRADA;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository repository;
    private final ReporteRepository reporteRepository;

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

        final Optional<Cuenta> cuenta = repository.findById(idCuenta);
        if (cuenta.isPresent()) {
            return Optional.of(repository.save(buildCuenta(cuentaRequest)));
        } else {
            throw new CuentaException(CUENTA_NO_ENCONTRADA);
        }
    }

    @Override
    public Consumer<? super Cuenta> borrarCuentaPorId(Long id) {
        final Optional<Cuenta> cuenta = repository.findById(id);

        if (cuenta.isPresent()) {
            repository.delete(cuenta.get());
        } else {
            throw new CuentaException(CUENTA_NO_ENCONTRADA);
        }

        return null;
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorId(long id) {
        final Optional<Cuenta> cuenta = repository.findById(id);
        return Optional.ofNullable(cuenta.orElseThrow(() -> new CuentaException(CUENTA_NO_ENCONTRADA)));
    }

    @Override
    public List<Reporte> obtenerCuentasRespotesDeCuentasPorFechas
            (String nombre, String fechaInicial, String fechaFinal) {
        return repository.obtenerReportes(nombre, fechaInicial, fechaFinal)
                .stream()
                .map((Reporte cuenta) -> toReporte(cuenta.getCuenta()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Cuenta buildCuenta(Cuenta cuenta) {
        return Cuenta.builder()
                .numeroDeCuenta(cuenta.getNumeroDeCuenta())
                .tipoDeCuenta(cuenta.getTipoDeCuenta())
                .saldoInicial(cuenta.getSaldoInicial())
                .estado(cuenta.getEstado())
                .build();
    }

    private Reporte toReporte(Cuenta cuenta) {
        final long totalDebitos = cuenta.getMovimientos()
                .stream()
                .filter(movimiento -> movimiento.getTipoMovimiento().equals(TipoMovimiento.DEBITO))
                .mapToLong(Movimiento::getIdMovimiento)
                .sum();
        final long totalCreditos = cuenta.getMovimientos()
                .stream()
                .filter(movimiento -> movimiento.getTipoMovimiento().equals(TipoMovimiento.CREDITO))
                .mapToLong(Movimiento::getIdMovimiento)
                .sum();
        final BigDecimal saldoFinal = cuenta.getSaldoInicial()
                .add(BigDecimal.valueOf(totalCreditos - totalDebitos));
        Reporte reporte = new Reporte();
        reporte.setCuenta(cuenta);
        reporte.setTotalDebitos(totalDebitos);
        reporte.setTotalCreditos(totalCreditos);
        reporte.setSaldoFinal(saldoFinal);
        return reporteRepository.save(reporte);
    }

}
