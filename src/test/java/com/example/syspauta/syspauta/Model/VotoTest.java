package com.example.syspauta.syspauta.Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VotoTest {
    @Test
    void testCriacaoVoto () {
        // Arrange
        Long id = 1L;
        LocalDateTime dataVoto = LocalDateTime.now();
        String voto = "SIM";
        String cpfAssociado = "12345678900";
        Sessao sessao = new Sessao();

        // Act
        Voto votoObj = new Voto(id, dataVoto, voto, cpfAssociado, sessao);

        // Assert
        assertNotNull(votoObj);
        assertEquals(id, votoObj.getId());
        assertEquals(dataVoto, votoObj.getDataVoto());
        assertEquals(voto, votoObj.getVoto());
        assertEquals(cpfAssociado, votoObj.getCpfAssociado());
        assertEquals(sessao, votoObj.getSessao());
    }

    @Test
    void testeVotoSettersAndGetters() {
        // Arrange
        Voto voto = new Voto();
        Long id = 1L;
        LocalDateTime dataVoto = LocalDateTime.now();
        String votoValue = "NAO";
        String cpfAssociado = "98765432100";
        Sessao sessao = new Sessao();

        // Act
        voto.setId(id);
        voto.setDataVoto(dataVoto);
        voto.setVoto(votoValue);
        voto.setCpfAssociado(cpfAssociado);
        voto.setSessao(sessao);

        // Assert
        assertEquals(id, voto.getId());
        assertEquals(dataVoto, voto.getDataVoto());
        assertEquals(votoValue, voto.getVoto());
        assertEquals(cpfAssociado, voto.getCpfAssociado());
        assertEquals(sessao, voto.getSessao());
    }

    @Test
    void testeVotoDefaultConstructor() {
        // Act
        Voto voto = new Voto();

        // Assert
        assertNotNull(voto);
        assertNull(voto.getId());
        assertNull(voto.getDataVoto());
        assertNull(voto.getVoto());
        assertNull(voto.getCpfAssociado());
        assertNull(voto.getSessao());
    }
}
