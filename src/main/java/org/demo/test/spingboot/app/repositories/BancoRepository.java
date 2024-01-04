package org.demo.test.spingboot.app.repositories;

import org.demo.test.spingboot.app.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Long> {

    List<Banco> findAll();
    Optional<Banco> findById(Long id);
    //void update(Banco banco);
}
