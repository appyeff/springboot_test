package org.demo.test.spingboot.app.repositories;

import org.demo.test.spingboot.app.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    //List<Cuenta> findAll();
    //Optional<Cuenta> findById(Long id);
    //void update(Cuenta cuenta);

    @Query("select c from Cuenta c where c.persona=?1")
    Optional<Cuenta> findByPersona(String persona);
}
