package org.demo.test.spingboot.app.services;

import java.math.BigDecimal;
import java.util.List;

import org.demo.test.spingboot.app.models.Cuenta;

public interface CuentaService {

    List<Cuenta> findAll();

    Cuenta findById(Long id);

    Cuenta save(Cuenta cuenta);

    void deleteById(Long id);

    int revisarTotalTransferencia(Long bancoId);

    BigDecimal revisarSaldo(Long cuentaId);

    void transferir(Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto, Long bancoId);
}
