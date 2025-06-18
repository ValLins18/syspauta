package com.example.syspauta.syspauta.Model;

import com.example.syspauta.syspauta.Model.Enums.StatusPauta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PautaTest {

    @Test
    void testeCriacaoPauta () {
        // Arrange
        Long id = 1L;
        String nome = "Test Pauta";
        String descricao = "Test Description";
        StatusPauta status = StatusPauta.ABERTA;

        // Act
        Pauta pauta = new Pauta(id, nome, descricao, status);

        // Assert
        assertNotNull(pauta);
        assertEquals(id, pauta.getId());
        assertEquals(nome, pauta.getNome());
        assertEquals(descricao, pauta.getDescricao());
        assertEquals(status, pauta.getStatusPauta());
    }

    @Test
    void testePautaSettersAndGetters() {
        // Arrange
        Pauta pauta = new Pauta();
        Long id = 1L;
        String nome = "Test Pauta";
        String descricao = "Test Description";
        StatusPauta status = StatusPauta.ABERTA;

        // Act
        pauta.setId(id);
        pauta.setNome(nome);
        pauta.setDescricao(descricao);
        pauta.setStatusPauta(status);

        // Assert
        assertEquals(id, pauta.getId());
        assertEquals(nome, pauta.getNome());
        assertEquals(descricao, pauta.getDescricao());
        assertEquals(status, pauta.getStatusPauta());
    }

    @Test
    void testePautaDefaultConstructor() {
        // Act
        Pauta pauta = new Pauta();

        // Assert
        assertNotNull(pauta);
        assertNull(pauta.getId());
        assertNull(pauta.getNome());
        assertNull(pauta.getDescricao());
        assertNull(pauta.getStatusPauta());
    }

}
