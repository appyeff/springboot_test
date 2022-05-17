package org.demo.test.spingboot.app;

import static org.junit.jupiter.api.Assertions.*;
import org.demo.test.spingboot.app.models.Cuenta;
import org.demo.test.spingboot.app.repositories.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



@DataJpaTest
public class IntegrationJpaTest {
    
    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    void testFindById() {
        Optional<Cuenta> cuenta = cuentaRepository.findById(1L);
        assertTrue(cuenta.isPresent());
        assertEquals("Yeferson", cuenta.orElseThrow().getPersona());
    }

    @Test
    void testFindByPersona() {
        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Yeferson");
        assertTrue(cuenta.isPresent());
        assertEquals("Yeferson", cuenta.orElseThrow().getPersona());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }

    @Test
    void testFindByPersonaThrowException() {
        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Rod");
        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);
        assertFalse(cuenta.isPresent());
    }

    @Test
    void testFindAll() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());
    }

    @Test
    void testSave() {
        // given
        Cuenta cuentaPepe = new Cuenta(null, "Pepe", new BigDecimal("3000"));

        // when
        Cuenta save = cuentaRepository.save(cuentaPepe);
        //Cuenta cuenta = cuentaRepository.findByPersona("Pepe").orElseThrow();
        Cuenta cuenta = cuentaRepository.findById(save.getId()).orElseThrow();

        // then
        assertEquals("Pepe", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
        //assertEquals(3, cuenta.getId());
    }

    @Test
    void testUpdate() {
        // given
        Cuenta cuentaPepe = new Cuenta(null, "Pepe", new BigDecimal("3000"));

        // when
        Cuenta save = cuentaRepository.save(cuentaPepe);
        //Cuenta cuenta = cuentaRepository.findByPersona("Pepe").orElseThrow();
        Cuenta cuenta = cuentaRepository.findById(save.getId()).orElseThrow();

        // then
        assertEquals("Pepe", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
        //assertEquals(3, cuenta.getId());

        // when
        cuenta.setSaldo(new BigDecimal("3800"));
        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);

        // then
        assertEquals("Pepe", cuentaActualizada.getPersona());
        assertEquals("3800", cuentaActualizada.getSaldo().toPlainString());
    }

    @Test
    void testDelete() {

        Cuenta cuenta = cuentaRepository.findById(2L).orElseThrow();
        assertEquals("Andres", cuenta.getPersona());

        cuentaRepository.delete(cuenta);

        assertThrows(NoSuchElementException.class, () -> {
            cuentaRepository.findByPersona("Andres").orElseThrow();
        });

        assertEquals(1, cuentaRepository.findAll().size());
    }
}
