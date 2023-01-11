package com.soulrebel.neoris.repository;

import com.soulrebel.neoris.entity.Reporte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends CrudRepository<Reporte, Long> {

}
