package com.example.syspauta.syspauta.Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessaoTest {

    @Test
    void testeCriacaoSessao () {
        // Arrange
        Long id = 1L;
        LocalDateTime dataAbertura = LocalDateTime.now();
        Pauta pauta = new Pauta();
        List<Voto> votos = new ArrayList<>();

        // Act
        Sessao sessao = new Sessao(id, dataAbertura, pauta, votos);

        // Assert
        assertNotNull(sessao);
        assertEquals(id, sessao.getId());
        assertEquals(dataAbertura, sessao.getDataAberturaSessao());
        assertEquals(dataAbertura.plusMinutes(10), sessao.getDataFechamentoSessao());
        assertEquals(pauta, sessao.getPauta());
        assertEquals(votos, sessao.getVotos());
        assertEquals(10, sessao.getDuracaoMinutos());
    }

    @Test
    void testeSessaoFullConstructor() {
        // Arrange
        Long id = 1L;
        LocalDateTime dataAbertura = LocalDateTime.now();
        LocalDateTime dataFechamento = dataAbertura.plusMinutes(15);
        Integer duracao = 15;
        Pauta pauta = new Pauta();
        List<Voto> votos = new ArrayList<>();

        // Act
        Sessao sessao = new Sessao(id, dataAbertura, dataFechamento, duracao, pauta, votos);

        // Assert
        assertNotNull(sessao);
        assertEquals(id, sessao.getId());
        assertEquals(dataAbertura, sessao.getDataAberturaSessao());
        assertEquals(dataFechamento, sessao.getDataFechamentoSessao());
        assertEquals(duracao, sessao.getDuracaoMinutos());
        assertEquals(pauta, sessao.getPauta());
        assertEquals(votos, sessao.getVotos());
    }

    @Test
    void testeSessaoDefaultConstructor() {
        // Act
        Sessao sessao = new Sessao();

        // Assert
        assertNotNull(sessao);
        assertNull(sessao.getId());
        assertNull(sessao.getDataAberturaSessao());
        assertNull(sessao.getDataFechamentoSessao());
        assertEquals(10, sessao.getDuracaoMinutos());
        assertNull(sessao.getPauta());
        assertNull(sessao.getVotos());
    }

    @Test
    void testeSessaoAberta() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Sessao sessao = new Sessao();
        sessao.setDataAberturaSessao(now);
        sessao.setDuracaoMinutos(10);

        // Act & Assert
        assertTrue(sessao.isSessaoAberta(now));
        assertTrue(sessao.isSessaoAberta(now.plusMinutes(5)));
        assertFalse(sessao.isSessaoAberta(now.minusMinutes(1)));
        assertFalse(sessao.isSessaoAberta(now.plusMinutes(11)));
    }

    @Test
    void testeSessaoNuncaAberta() {
        // Arrange
        Sessao sessao = new Sessao();

        // Act & Assert
        assertFalse(sessao.isSessaoAberta());
    }

    @Test
    void testeSessaoSettersAndGetters() {
        // Arrange
        Sessao sessao = new Sessao();
        Long id = 1L;
        LocalDateTime dataAbertura = LocalDateTime.now();
        LocalDateTime dataFechamento = dataAbertura.plusMinutes(15);
        Integer duracao = 15;
        Pauta pauta = new Pauta();
        List<Voto> votos = new ArrayList<>();

        // Act
        sessao.setId(id);
        sessao.setDataAberturaSessao(dataAbertura);
        sessao.setDataFechamentoSessao(dataFechamento);
        sessao.setDuracaoMinutos(duracao);
        sessao.setPauta(pauta);
        sessao.setVotos(votos);

        // Assert
        assertEquals(id, sessao.getId());
        assertEquals(dataAbertura, sessao.getDataAberturaSessao());
        assertEquals(dataFechamento, sessao.getDataFechamentoSessao());
        assertEquals(duracao, sessao.getDuracaoMinutos());
        assertEquals(pauta, sessao.getPauta());
        assertEquals(votos, sessao.getVotos());
    }
}
