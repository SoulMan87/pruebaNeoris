package com.soulrebel.neoris.repository;

import com.soulrebel.neoris.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {
}
