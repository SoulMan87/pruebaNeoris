package com.soulrebel.neoris.controller;

import com.soulrebel.neoris.entity.Cuenta;
import com.soulrebel.neoris.entity.Reporte;
import com.soulrebel.neoris.model.CuentaRespuesta;
import com.soulrebel.neoris.model.ReporteRespuesta;
import com.soulrebel.neoris.service.CuentaService;
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
public class CuentaController implements ICuentaController {

    private final CuentaService service;

    private final ModelMapper modelMapper;

    @Override
    public List<CuentaRespuesta> obtenerTodosLasCuentas() {

        return service.encontrarTodosLasCuentas()
                .stream().map(cuenta -> modelMapper.map(cuenta, CuentaRespuesta.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<CuentaRespuesta> obtenerCuentaPorId(Long id) {
        Optional<Cuenta> cuenta = service.obtenerCuentaPorId(id);

        CuentaRespuesta cuentaRespuesta = modelMapper.map(cuenta, CuentaRespuesta.class);

        return ResponseEntity.ok().body(cuentaRespuesta);
    }

    @Override
    public ResponseEntity<CuentaRespuesta> actualizarCuenta(Long id, CuentaRespuesta cuentaRespuesta) {

        Cuenta cuentaRequest = modelMapper.map(cuentaRespuesta, Cuenta.class);

        Optional<Cuenta> actualizarCuentacuenta = service.actualizarCuenta(id, cuentaRequest);

        CuentaRespuesta cuenta = modelMapper.map(actualizarCuentacuenta, CuentaRespuesta.class);
        return ResponseEntity.ok().body(cuenta);
    }

    @Override
    public ResponseEntity<Void> borrarCuentaPorId(Long id) {
        service.obtenerCuentaPorId(id).ifPresent(service.borrarCuentaPorId(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CuentaRespuesta> crearCuenta(CuentaRespuesta cuentaRespuesta) {

        Cuenta cuentaRequest = modelMapper.map(cuentaRespuesta, Cuenta.class);

        Optional<Cuenta> crearCuenta = service.crearCuenta(cuentaRequest);

        CuentaRespuesta cuenta = modelMapper.map(crearCuenta, CuentaRespuesta.class);

        return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ReporteRespuesta>> obtenerCuentasRespotesDeCuentasPorFechas(
            String nombre, String fechaFinal, String fechaInicial) {

        final List<Reporte> reportes = service.obtenerCuentasRespotesDeCuentasPorFechas(nombre, fechaFinal, fechaFinal);
        return ResponseEntity.ok(reportes.stream()
                .map(this::convertirCuentaToCuentaRespuesta)
                .collect(Collectors.toList()));
    }

    private ReporteRespuesta convertirCuentaToCuentaRespuesta(Reporte reporte) {
        return modelMapper.map(reporte, ReporteRespuesta.class);
    }
}
